package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.model.CodeContainer;
import platform.repository.CodeContainerRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CodeContainerService {

    @Autowired
    private CodeContainerRepository repository;

    public CodeContainer findById(String id) {
        Optional<CodeContainer> optionalCodeContainer = repository.findById(id);

        if (optionalCodeContainer.isEmpty()) {
            Exception e = new RuntimeException(String.format("Code ID (%s) not found", id));
            e.printStackTrace();

            return new CodeContainer();
        }

        return optionalCodeContainer.get();
    }

    public List<CodeContainer> findLatest() {
        List<CodeContainer> codes = new ArrayList<>();
        repository.findAll().forEach(codes::add);

        return codes.stream()
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
    }

    public void save (CodeContainer codeContainer) {
        repository.save(codeContainer);
    }
}
