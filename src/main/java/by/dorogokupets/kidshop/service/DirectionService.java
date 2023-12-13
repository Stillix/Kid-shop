package by.dorogokupets.kidshop.service;

import by.dorogokupets.kidshop.domain.model.Direction;
import by.dorogokupets.kidshop.domain.dto.DirectionDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DirectionService {
  void update(DirectionDto directionDto);

  Page<Direction> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);
  List<Direction> findAll();

  void save(DirectionDto directionDto);

  DirectionDto findDirectionDtoById(Long id);
}
