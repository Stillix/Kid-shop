package by.dorogokupets.cvservice.service;


import by.dorogokupets.cvservice.domain.dto.TestDto;
import by.dorogokupets.cvservice.domain.model.Test;
import org.springframework.data.domain.Page;

public interface TestService {
  Page<Test> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);

  TestDto findTestDtoById(Long id);

  void update(TestDto testDto);

  void save(TestDto TestDto);
}
