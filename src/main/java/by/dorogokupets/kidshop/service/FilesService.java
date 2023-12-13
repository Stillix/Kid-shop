package by.dorogokupets.kidshop.service;

import by.dorogokupets.kidshop.domain.model.Candidate;
import by.dorogokupets.kidshop.domain.model.FileDB;

public interface FilesService {

  FileDB getFile(Long fileId);

  FileDB findByCandidateAndContentType(Candidate candidate, String contentType);
}
