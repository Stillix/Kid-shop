package by.dorogokupets.cvservice.repository;

import by.dorogokupets.cvservice.domain.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {

  Optional<Direction> findById(Long id);

}
