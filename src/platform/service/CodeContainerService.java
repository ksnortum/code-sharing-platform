package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.model.CodeContainer;
import platform.repository.CodeContainerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CodeContainerService {

    @Autowired
    private CodeContainerRepository repository;

    public Optional<CodeContainer> findById(String id) {
        return repository.findById(id);
    }

    public List<CodeContainer> findLatest() {
//        List<CodeContainer> codes = new ArrayList<>();
//        repository.findAll().forEach(codes::add);
//
//        return codes.stream()
//                .filter(code -> code.getTime() == 0 && code.getViews() == 0)
//                .sorted()
//                .limit(10)
//                .collect(Collectors.toList());
        return repository.findTop10ByTimeEqualsAndViewsEqualsOrderByDateDesc(0, 0);
    }

    public void save(CodeContainer codeContainer) {
        repository.save(codeContainer);
    }

    public void delete(CodeContainer codeContainer) {
        repository.delete(codeContainer);
    }
}
