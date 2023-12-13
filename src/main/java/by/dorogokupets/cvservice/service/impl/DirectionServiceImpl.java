package by.dorogokupets.cvservice.service.impl;

import by.dorogokupets.cvservice.domain.dto.DirectionDto;
import by.dorogokupets.cvservice.mapper.DirectionMapper;
import by.dorogokupets.cvservice.domain.model.Direction;
import by.dorogokupets.cvservice.service.DirectionService;
import by.dorogokupets.cvservice.repository.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectionServiceImpl implements DirectionService {
  private final DirectionRepository directionRepository;
  private final DirectionMapper directionMapper;

  @Autowired
  public DirectionServiceImpl(DirectionRepository directionRepository, DirectionMapper directionMapper) {
    this.directionRepository = directionRepository;
    this.directionMapper = directionMapper;
  }

  @Override
  public Page<Direction> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
    Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    return directionRepository.findAll(pageable);
  }

  @Override
  public List<Direction> findAll() {
    return directionRepository.findAll();
  }

  @Override
  public void save(DirectionDto directionDto) {
    Direction direction = directionMapper.mapToDirection(directionDto);
    directionRepository.save(direction);
  }

  @Override
  public DirectionDto findDirectionDtoById(Long id) {
    Optional<Direction> direction = directionRepository.findById(id);
    return direction.map(directionMapper::mapToDirectionDTO).orElse(null);
  }

  @Override
  public void update(DirectionDto directionDto) {
    Direction direction = directionMapper.mapToDirection(directionDto);
    directionRepository.save(direction);
  }
}

