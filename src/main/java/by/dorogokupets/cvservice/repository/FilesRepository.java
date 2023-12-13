package by.dorogokupets.cvservice.repository;

import by.dorogokupets.cvservice.domain.model.Candidate;
import by.dorogokupets.cvservice.domain.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilesRepository extends JpaRepository<FileDB, Long> {

  FileDB findByCandidateAndContentType(Candidate candidate, String contentType);
}
