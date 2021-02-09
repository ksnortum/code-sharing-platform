package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import platform.model.CodeContainer;
import platform.service.CodeContainerService;

@Controller
public class CodeController {
    @Autowired
    private CodeContainerService codeContainerService;

    @GetMapping(value = "/code", produces = "text/html")
    public @ResponseBody String getCodeWeb() {
        CodeContainer codeContainer = codeContainerService.getCodeContainer();

        return  "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "   <title>Code</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "   <span id=\"load_date\">" +
                        codeContainer.getDate() +
                "   </span>\n" +
                "   <pre id=\"code_snippet\">\n" +
                        codeContainer.getCode() + "\n" +
                "</pre>\n" +
                "</body>\n" +
                "</html>";
    }

    @GetMapping("/code/new")
    public @ResponseBody String formCodeWed() {
        return  "<!DOCTYPE html> " +
                "<html lang=\"en\"> " +
                "<head> " +
                "<meta charset=\"utf-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width\"> " +
                "<title>Create</title> " +
                //"<link href=\"css/get-code.css\" rel=\"stylesheet\" type=\"text/css\" /> " +
                "<style>body { background-color: lightblue; }</style>" +
                "</head> " +
                "<body> " +
                //"<script src=\"js/script.js\"></script> " +
                "<script>" +
                "function send() {\n" +
                "  let object = {\n" +
                "    \"code\": document.getElementById(\"code_snippet\").value\n" +
                "  };\n" +
                "  let json = JSON.stringify(object);\n" +
                "  let xhr = new XMLHttpRequest();\n" +
                "  xhr.open(\"POST\", '/api/code/new', false)\n" +
                "  xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "  xhr.send(json);\n" +
                "  if (xhr.status == 200) {\n" +
                "    alert(\"Success!\");\n" +
                "  }\n" +
                "}" +
                "</script>" +
                "<form> " +
                "<textarea id=\"code_snippet\">// Write your code here</textarea> " +
                "</form> " +
                "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button> " +
                "</body> " +
                "</html> ";
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json")
    public ResponseEntity<String> saveCodeRest(@RequestBody CodeContainer codeContainer) {
        codeContainerService.save(codeContainer);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @GetMapping(value = "/api/code", produces = "application/json")
    public @ResponseBody CodeContainer getCodeRest() {
        return codeContainerService.getCodeContainer();
    }

}
