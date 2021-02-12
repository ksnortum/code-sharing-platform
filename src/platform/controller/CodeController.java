package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.model.CodeContainer;
import platform.service.CodeContainerService;

@Controller
public class CodeController {
    @Autowired
    private CodeContainerService codeContainerService;

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String getCodeByIdWeb(@PathVariable int id, Model model) {
        CodeContainer codeContainer = codeContainerService.getById(id);
        model.addAttribute("codeContainer", codeContainer);

        return "code-by-id";
    }

    @GetMapping("/code/new")
    public String createCodeWeb() {
        return "create-code";
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json")
    public ResponseEntity<String> saveCodeRest(@RequestBody CodeContainer codeContainer) {
        codeContainerService.save(codeContainer);
        return new ResponseEntity<>(String.format("{ \"id\" : \"%d\" }", codeContainer.getId()), HttpStatus.OK);
    }

    @GetMapping(value = "/api/code/{id}", produces = "application/json")
    public @ResponseBody CodeContainer getCodeByIdRest(@PathVariable int id) {
        return codeContainerService.getById(id);
    }

}
