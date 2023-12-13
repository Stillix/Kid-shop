package by.dorogokupets.cvservice.mapper;

import by.dorogokupets.cvservice.domain.dto.DirectionDto;
import by.dorogokupets.cvservice.domain.model.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Controller;

@Mapper
@Controller
public interface DirectionMapper {
  DirectionMapper MAPPER = Mappers.getMapper(DirectionMapper.class);

  DirectionDto mapToDirectionDTO(Direction direction);

  Direction mapToDirection(DirectionDto directionDto);
}
