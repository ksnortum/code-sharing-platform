package platform.service;

import org.springframework.stereotype.Service;
import platform.model.CodeContainer;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class CodeContainerService {
    private static final List<String> CODE = Arrays.asList(
            "public static void main(String[] args) {",
            "    SpringApplication.run(CodeSharingPlatform.class, args);",
            "}"
    );

    // field with default values
    private CodeContainer codeContainer =
            new CodeContainer(String.join("\n", CODE));

    public CodeContainer getCodeContainer() {
        return codeContainer;
    }

    public void save (CodeContainer codeContainer) {
        this.codeContainer = codeContainer;
    }
}
