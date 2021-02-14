package platform.repository;

import org.springframework.data.repository.CrudRepository;
import platform.model.CodeContainer;

import java.util.Optional;

public interface CodeContainerRepository extends CrudRepository<CodeContainer, String> { }