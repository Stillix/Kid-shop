package by.dorogokupets.kidshop.repository;

import by.dorogokupets.kidshop.domain.model.TestResult;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
}
