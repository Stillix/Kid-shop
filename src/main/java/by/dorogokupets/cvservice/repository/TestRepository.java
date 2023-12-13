package by.dorogokupets.cvservice.repository;

import by.dorogokupets.cvservice.domain.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

  Optional<Test> findById(Long testId);
}
