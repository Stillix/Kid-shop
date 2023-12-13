package by.dorogokupets.cvservice.service;

import by.dorogokupets.cvservice.domain.model.Candidate;
import by.dorogokupets.cvservice.domain.model.FileDB;

public interface FilesService {

  FileDB getFile(Long fileId);

  FileDB findByCandidateAndContentType(Candidate candidate, String contentType);
}
