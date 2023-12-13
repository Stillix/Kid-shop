package by.dorogokupets.kidshop.mapper;

import by.dorogokupets.kidshop.domain.dto.DirectionDto;
import by.dorogokupets.kidshop.domain.model.Direction;
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
