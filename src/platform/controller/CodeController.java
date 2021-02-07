package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.model.CodeContainer;
import platform.service.CodeContainerService;

@Controller
public class CodeController {
    @Autowired
    private CodeContainerService codeContainerService;

    @GetMapping(value = "/code", produces = "text/html")
    @ResponseBody
    public String getCodeWeb() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("    <title>Code</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("    <pre>\n");
        sb.append(codeContainerService.getCodeContainer().getCode());
        sb.append("</pre>\n");
        sb.append("</body>\n");
        sb.append("</html>");

        return sb.toString();
    }

    @GetMapping(value = "/api/code", produces = "application/json")
    public @ResponseBody CodeContainer getCodeRest() {
        return codeContainerService.getCodeContainer();
    }
}
