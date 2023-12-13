package by.dorogokupets.cvservice.service;

import by.dorogokupets.cvservice.domain.dto.CandidateDto;
import by.dorogokupets.cvservice.exception.ServiceException;
import by.dorogokupets.cvservice.domain.model.Candidate;
import org.springframework.data.domain.Page;

public interface CandidateService {
  Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);

  Candidate findCandidateById(Long candidateId);

  void update(CandidateDto candidateDto) throws ServiceException;

  void save(CandidateDto candidateDto) throws ServiceException;

  void delete(Long candidateId);
  CandidateDto findCandidateDtoById(Long id);
}
