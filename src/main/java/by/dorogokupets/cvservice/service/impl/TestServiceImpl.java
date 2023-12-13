package by.dorogokupets.cvservice.service.impl;

import by.dorogokupets.cvservice.domain.dto.TestDto;
import by.dorogokupets.cvservice.mapper.TestMapper;
import by.dorogokupets.cvservice.domain.model.Test;
import by.dorogokupets.cvservice.service.TestService;
import by.dorogokupets.cvservice.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {
  private final TestRepository testRepository;
  private final TestMapper testMapper;

  @Autowired
  public TestServiceImpl(TestRepository testRepository, TestMapper testMapper) {
    this.testRepository = testRepository;
    this.testMapper = testMapper;
  }

  @Override
  public Page<Test> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
    Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    return testRepository.findAll(pageable);
  }

  @Override
  public TestDto findTestDtoById(Long id) {
    Optional<Test> test = testRepository.findById(id);
    return test.map(testMapper::mapToTestDTO).orElse(null);
  }

  @Override
  public void update(TestDto testDto) {
    Test test = testMapper.mapToTest(testDto);
    testRepository.save(test);
  }

  @Override
  public void save(TestDto testDto) {
    Test test = testMapper.mapToTest(testDto);
    testRepository.save(test);
  }
}
