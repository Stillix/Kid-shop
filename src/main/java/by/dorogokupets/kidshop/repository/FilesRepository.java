package by.dorogokupets.kidshop.repository;

import by.dorogokupets.kidshop.domain.model.User;
import by.dorogokupets.kidshop.domain.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<FileDB, Long> {

  FileDB findByCandidateAndContentType(User user, String contentType);
}
