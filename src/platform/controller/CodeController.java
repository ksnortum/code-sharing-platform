package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.exception.CodeContainerIsHiddenException;
import platform.exception.CodeContainerMissingException;
import platform.model.CodeContainer;
import platform.service.CodeContainerService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class CodeController {
    @Autowired
    private CodeContainerService codeContainerService;

    @GetMapping("/code/new")
    public String createCodeWeb() {
        return "create-code";
    }

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String findCodeByIdWeb(@PathVariable String id, Model model) {
        Optional<CodeContainer> codeContainerOptional = codeContainerService.findById(id);

        if (codeContainerOptional.isEmpty()) {
            throw new CodeContainerMissingException();
        }

        CodeContainer codeContainer = codeContainerOptional.get();

        if (codeContainer.isHidden()) {
            codeContainerService.delete(codeContainer);
            throw new CodeContainerIsHiddenException();
        }

        codeContainer.incrementNumberOfTimesViewed();
        codeContainerService.save(codeContainer);
        model.addAttribute("codeContainer", codeContainer);

        return "code-by-id";
    }

    @GetMapping("/code/latest")
    public String findLatestCodesWeb(Model model) {
        List<CodeContainer> codes = codeContainerService.findLatest();
        model.addAttribute("codes", codes);

        return "latest-code";
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json")
    public ResponseEntity<String> saveCodeRest(@RequestBody CodeContainer codeContainer) {
        codeContainerService.save(codeContainer);

        return new ResponseEntity<>(String.format("{ \"id\" : \"%s\" }", codeContainer.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/api/code/{id}", produces = "application/json")
    public @ResponseBody CodeContainer findCodeByIdRest(@PathVariable String id, HttpServletResponse response) {
        Optional<CodeContainer> codeContainerOptional = codeContainerService.findById(id);

        if (codeContainerOptional.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return new CodeContainer();
        }

        CodeContainer codeContainer = codeContainerOptional.get();

        if (codeContainer.isHidden()) {
            codeContainerService.delete(codeContainer);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            return new CodeContainer();
        }

        codeContainer.incrementNumberOfTimesViewed();
        codeContainerService.save(codeContainer);

        return codeContainer;
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public @ResponseBody List<CodeContainer> findLatestCodesRest() {
        return codeContainerService.findLatest();
    }
}
