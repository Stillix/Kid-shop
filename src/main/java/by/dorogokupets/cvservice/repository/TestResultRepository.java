package by.dorogokupets.cvservice.repository;

import by.dorogokupets.cvservice.domain.model.TestResult;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
}
