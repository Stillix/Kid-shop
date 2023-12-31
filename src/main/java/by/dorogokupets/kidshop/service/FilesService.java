package by.dorogokupets.kidshop.service;

import by.dorogokupets.kidshop.domain.model.User;
import by.dorogokupets.kidshop.domain.model.FileDB;

public interface FilesService {

  FileDB getFile(Long fileId);

  FileDB findByCandidateAndContentType(User user, String contentType);
}
