package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.model.CodeContainer;
import platform.service.CodeContainerService;

import java.util.List;

@Controller
public class CodeController {
    @Autowired
    private CodeContainerService codeContainerService;

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String findCodeByIdWeb(@PathVariable int id, Model model) {
        CodeContainer codeContainer = codeContainerService.findById(id);
        model.addAttribute("codeContainer", codeContainer);

        return "code-by-id";
    }

    @GetMapping("/code/new")
    public String createCodeWeb() {
        return "create-code";
    }

    @GetMapping("/code/latest")
    public String findLatestCodes(Model model) {
        List<CodeContainer> codes = codeContainerService.findLatest();
        model.addAttribute("codes", codes);

        return "latest-code";
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json")
    public ResponseEntity<String> saveCodeRest(@RequestBody CodeContainer codeContainer) {
        codeContainerService.save(codeContainer);

        return new ResponseEntity<>(String.format("{ \"id\" : \"%d\" }", codeContainer.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/api/code/{id}", produces = "application/json")
    public @ResponseBody CodeContainer findCodeByIdRest(@PathVariable long id) {
        return codeContainerService.findById(id);
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public @ResponseBody List<CodeContainer> findLatestCodeEntries() {
        return codeContainerService.findLatest();
    }

}
