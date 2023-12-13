package by.dorogokupets.cvservice.service.impl;

import by.dorogokupets.cvservice.domain.dto.CandidateDto;
import by.dorogokupets.cvservice.exception.ServiceException;
import by.dorogokupets.cvservice.mapper.CandidateMapper;
import by.dorogokupets.cvservice.domain.model.Candidate;
import by.dorogokupets.cvservice.domain.model.FileDB;
import by.dorogokupets.cvservice.repository.FilesRepository;
import by.dorogokupets.cvservice.service.CandidateService;
import by.dorogokupets.cvservice.repository.CandidateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {
  private final CandidateRepository candidateRepository;
  private final FilesRepository filesRepository;
  private final CandidateMapper candidateMapper;

  public CandidateServiceImpl(CandidateRepository candidateRepository,
                              FilesRepository filesRepository,
                              CandidateMapper candidateMapper) {
    this.candidateRepository = candidateRepository;
    this.filesRepository = filesRepository;
    this.candidateMapper = candidateMapper;
  }

  @Override
  public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
    Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    return candidateRepository.findAll(pageable);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(CandidateDto candidateDto) throws ServiceException {
    Candidate candidate = candidateMapper.mapToCandidate(candidateDto);

    FileDB cvFileDb = convertToFileDB(candidateDto.getCvFile());
    FileDB imageFileDb = convertToFileDB(candidateDto.getPhoto());

    Candidate savedCandidate = candidateRepository.save(candidate);

    cvFileDb.setCandidate(savedCandidate);
    imageFileDb.setCandidate(savedCandidate);

    filesRepository.save(cvFileDb);
    filesRepository.save(imageFileDb);
  }

  private FileDB convertToFileDB(MultipartFile cvFile) throws ServiceException {
    FileDB csFileDb = new FileDB();
    csFileDb.setName(StringUtils.cleanPath(cvFile.getOriginalFilename()));
    csFileDb.setContentType(cvFile.getContentType());
    try {
      csFileDb.setData(cvFile.getBytes());
    } catch (IOException e) {
      throw new ServiceException(e);
    }
    csFileDb.setSize(cvFile.getSize());
    return csFileDb;
  }

  @Override
  public void delete(Long candidateId) {
    candidateRepository.deleteById(candidateId);
  }

  @Override
  public CandidateDto findCandidateDtoById(Long id) {
    Optional<Candidate> candidate = candidateRepository.findById(id);
    return candidate.map(candidateMapper::mapToCandidateDTO).orElse(null);
  }

  @Override
  public Candidate findCandidateById(Long candidateId) {
    return candidateRepository.findById(candidateId).orElse(null);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void update(CandidateDto candidateDto) throws ServiceException {
    Candidate currentCandidate = candidateRepository.getReferenceById(candidateDto.getCandidateId());
    currentCandidate.setDescription(candidateDto.getDescription());
    currentCandidate.setPatronymic(candidateDto.getPatronymic());
    currentCandidate.setFirstName(candidateDto.getFirstName());
    currentCandidate.setLastName(candidateDto.getLastName());
    currentCandidate.setDirection(candidateDto.getDirection());
    FileDB cvFile = filesRepository.findByCandidateAndContentType(currentCandidate, MediaType.APPLICATION_PDF_VALUE);
    FileDB image = filesRepository.findByCandidateAndContentType(currentCandidate, MediaType.IMAGE_PNG_VALUE);

    MultipartFile cvFileToUpdate = candidateDto.getCvFile();
    MultipartFile imageFileToUpdate = candidateDto.getPhoto();

    if (cvFileToUpdate != null) {
      if (cvFile == null) {
        cvFile = convertToFileDB(cvFileToUpdate);
        cvFile.setCandidate(currentCandidate);
      } else {
        updateFileDB(cvFile, cvFileToUpdate);
      }
      filesRepository.save(cvFile);
    }

    if (imageFileToUpdate != null) {
      if (image == null) {
        image = convertToFileDB(imageFileToUpdate);
        image.setCandidate(currentCandidate);
      } else {
        updateFileDB(image, imageFileToUpdate);
      }
      filesRepository.save(image);
    }
    candidateRepository.save(currentCandidate);
  }

  private void updateFileDB(FileDB fileDB, MultipartFile fileToUpdate) throws ServiceException {
    fileDB.setSize(fileToUpdate.getSize());
    fileDB.setName(StringUtils.cleanPath(fileToUpdate.getOriginalFilename()));
    fileDB.setContentType(fileDB.getContentType());
    try {
      fileDB.setData(fileToUpdate.getBytes());
    } catch (IOException e) {
      throw new ServiceException(e);
    }
  }
}
