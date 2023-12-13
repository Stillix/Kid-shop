package by.dorogokupets.kidshop.service;

import by.dorogokupets.kidshop.domain.dto.CandidateDto;
import by.dorogokupets.kidshop.domain.model.User;
import by.dorogokupets.kidshop.exception.ServiceException;
import org.springframework.data.domain.Page;

public interface CandidateService {
  Page<User> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);

  User findCandidateById(Long candidateId);

  void update(CandidateDto candidateDto) throws ServiceException;

  void save(CandidateDto candidateDto) throws ServiceException;

  void delete(Long candidateId);
  CandidateDto findCandidateDtoById(Long id);
}
