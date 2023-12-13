package by.dorogokupets.kidshop.mapper;

import by.dorogokupets.kidshop.domain.dto.TestResultDto;
import by.dorogokupets.kidshop.domain.model.TestResult;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Controller;

@Mapper
@Controller
public interface TestResultMapper {
  TestResultDto mapToTestResultDTO(TestResult testResult);

  TestResult mapToTestResult(TestResultDto testResultDto);
}
