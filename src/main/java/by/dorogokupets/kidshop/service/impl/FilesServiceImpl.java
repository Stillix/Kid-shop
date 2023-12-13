package by.dorogokupets.kidshop.service.impl;

import by.dorogokupets.kidshop.domain.model.Candidate;
import by.dorogokupets.kidshop.domain.model.FileDB;
import by.dorogokupets.kidshop.repository.FilesRepository;
import by.dorogokupets.kidshop.service.FilesService;
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
