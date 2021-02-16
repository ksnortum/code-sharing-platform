package platform.repository;

import org.springframework.data.repository.CrudRepository;
import platform.model.CodeContainer;

import java.util.List;

public interface CodeContainerRepository extends CrudRepository<CodeContainer, String> {
    List<CodeContainer> findTop10ByTimeEqualsAndViewsEqualsOrderByDateDesc(int time, int views);
}