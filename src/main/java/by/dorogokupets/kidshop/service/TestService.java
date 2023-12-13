package by.dorogokupets.kidshop.service;


import by.dorogokupets.kidshop.domain.dto.TestDto;
import by.dorogokupets.kidshop.domain.model.Test;
import org.springframework.data.domain.Page;

public interface TestService {
  Page<Test> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);

  TestDto findTestDtoById(Long id);

  void update(TestDto testDto);

  void save(TestDto TestDto);
}
