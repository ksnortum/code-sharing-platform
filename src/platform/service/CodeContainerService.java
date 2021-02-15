package platform.service;

import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.model.CodeContainer;
import platform.repository.CodeContainerRepository;

import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CodeContainerService {

    @Autowired
    private CodeContainerRepository repository;

    public Optional<CodeContainer> findById(String id) {
        return repository.findById(id);
    }

    public List<CodeContainer> findLatest() {
        List<CodeContainer> codes = new ArrayList<>();

        for (CodeContainer code : repository.findAll()) {
            if (code.isHidden()) {
                repository.delete(code);
            } else {
                codes.add(code);
            }
        }

        codes = codes.stream()
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
        codes.forEach(CodeContainer::incrementNumberOfTimesViewed);
        codes.forEach(code -> repository.save(code));

        return codes;
    }

    public void save(CodeContainer codeContainer) {
        repository.save(codeContainer);
    }

    public void delete(CodeContainer codeContainer) {
        repository.delete(codeContainer);
    }
}
