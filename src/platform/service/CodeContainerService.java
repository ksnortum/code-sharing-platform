package platform.service;

import org.springframework.stereotype.Service;
import platform.model.CodeContainer;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeContainerService {
    private final List<CodeContainer> repository = new ArrayList<>();

    public CodeContainer getById(int id) {
        return repository.get(id);
    }

    public List<CodeContainer> getAll() {
        return repository;
    }

    public void save (CodeContainer codeContainer) {
        repository.add(codeContainer);

        if (!repository.get(codeContainer.getId()).equals(codeContainer)) {
            Exception e = new RuntimeException("Code container ID does not match repository ID");
            e.printStackTrace();
        }
    }
}
