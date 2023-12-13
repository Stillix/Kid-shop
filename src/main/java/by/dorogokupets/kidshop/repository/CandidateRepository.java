package by.dorogokupets.kidshop.repository;

import by.dorogokupets.kidshop.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<User, Long> {

  Optional<User> findById(Long candidateId);

}
