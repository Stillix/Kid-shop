package by.dorogokupets.cvservice.service.impl;

import by.dorogokupets.cvservice.domain.model.Candidate;
import by.dorogokupets.cvservice.domain.model.FileDB;
import by.dorogokupets.cvservice.repository.FilesRepository;
import by.dorogokupets.cvservice.service.FilesService;
import org.springframework.stereotype.Service;

@Service
public class FilesServiceImpl implements FilesService {

  private final FilesRepository filesRepository;

  public FilesServiceImpl(FilesRepository filesRepository) {
    this.filesRepository = filesRepository;
  }

  @Override
  public FileDB getFile(Long fileId) {
    return filesRepository.getReferenceById(fileId);
  }

  @Override
  public FileDB findByCandidateAndContentType(Candidate candidate, String contentType) {
    return filesRepository.findByCandidateAndContentType(candidate, contentType);
  }
}
