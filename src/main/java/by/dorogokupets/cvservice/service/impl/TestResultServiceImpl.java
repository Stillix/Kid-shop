package by.dorogokupets.cvservice.service.impl;

import by.dorogokupets.cvservice.domain.dto.TestResultDto;
import by.dorogokupets.cvservice.mapper.TestResultMapper;
import by.dorogokupets.cvservice.domain.model.TestResult;
import by.dorogokupets.cvservice.service.TestResultService;
import by.dorogokupets.cvservice.repository.TestResultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestResultServiceImpl implements TestResultService {
  private final TestResultRepository testResultRepository;
  private final TestResultMapper testResultMapper;

  public TestResultServiceImpl(TestResultRepository testResultRepository, TestResultMapper testResultMapper) {
    this.testResultRepository = testResultRepository;
    this.testResultMapper = testResultMapper;
  }

  @Override
  public Page<TestResult> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
    Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    return testResultRepository.findAll(pageable);
  }

  @Override
  public TestResultDto findTestResultDtoById(Long id) {
    Optional<TestResult> testResult = testResultRepository.findById(id);
    return testResult.map(testResultMapper::mapToTestResultDTO).orElse(null);
  }

  @Override
  public void update(TestResultDto testResultDto) {
    TestResult testResult = testResultMapper.mapToTestResult(testResultDto);
    testResultRepository.save(testResult);
  }

  @Override
  public void save(TestResultDto testResultDto) {
    TestResult testResult = testResultMapper.mapToTestResult(testResultDto);
    testResultRepository.save(testResult);
  }
}
