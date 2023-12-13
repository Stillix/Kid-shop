package by.dorogokupets.cvservice.service;

import by.dorogokupets.cvservice.domain.dto.TestResultDto;
import by.dorogokupets.cvservice.domain.model.TestResult;
import org.springframework.data.domain.Page;

public interface TestResultService {
  Page<TestResult> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);

  TestResultDto findTestResultDtoById(Long id);

  void update(TestResultDto testResultDto);

  void save(TestResultDto testResultDto);
}
