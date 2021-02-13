package platform.service;

import org.springframework.stereotype.Service;
import platform.model.CodeContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CodeContainerService {
    private final Map<Integer, CodeContainer> repository = new HashMap<>();

    public CodeContainer getById(int id) {
        return repository.get(id);
    }

    public List<CodeContainer> getLatest() {
        List<CodeContainer> codes = new ArrayList<>(repository.values());

        return codes.stream()
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
    }

    public void save (CodeContainer codeContainer) {
        repository.put(codeContainer.getId(), codeContainer);
    }
}
