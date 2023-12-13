package by.dorogokupets.kidshop.mapper;

import by.dorogokupets.kidshop.domain.dto.TestDto;
import by.dorogokupets.kidshop.domain.model.Test;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Controller;

@Mapper
@Controller
public interface TestMapper {

  TestDto mapToTestDTO(Test test);

  Test mapToTest(TestDto testDto);
}
