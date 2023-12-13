package by.dorogokupets.kidshop.repository;

import by.dorogokupets.kidshop.domain.model.Candidate;
import by.dorogokupets.kidshop.domain.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<FileDB, Long> {

  FileDB findByCandidateAndContentType(Candidate candidate, String contentType);
}
