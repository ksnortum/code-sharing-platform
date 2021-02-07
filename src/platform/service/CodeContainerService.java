package platform.service;

import org.springframework.stereotype.Service;
import platform.model.CodeContainer;

import java.util.Arrays;
import java.util.List;

@Service
public class CodeContainerService {
    private final List<String> code = Arrays.asList(
            "public static void main(String[] args) {",
            "    SpringApplication.run(CodeSharingPlatform.class, args);",
            "}"
    );

    public CodeContainer getCodeContainer() {
        return new CodeContainer(String.join("\n", code));
    }
}
