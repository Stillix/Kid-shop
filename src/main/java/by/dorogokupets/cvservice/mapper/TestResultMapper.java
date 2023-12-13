package by.dorogokupets.cvservice.mapper;

import by.dorogokupets.cvservice.domain.dto.TestResultDto;
import by.dorogokupets.cvservice.domain.model.TestResult;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Controller;

@Mapper
@Controller
public interface TestResultMapper {
  TestResultDto mapToTestResultDTO(TestResult testResult);

  TestResult mapToTestResult(TestResultDto testResultDto);
}
