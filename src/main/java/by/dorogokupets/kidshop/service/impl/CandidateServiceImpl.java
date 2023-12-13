package by.dorogokupets.kidshop.service.impl;

import by.dorogokupets.kidshop.domain.dto.CandidateDto;
import by.dorogokupets.kidshop.domain.model.User;
import by.dorogokupets.kidshop.exception.ServiceException;
import by.dorogokupets.kidshop.mapper.CandidateMapper;
import by.dorogokupets.kidshop.domain.model.FileDB;
import by.dorogokupets.kidshop.repository.FilesRepository;
import by.dorogokupets.kidshop.service.CandidateService;
import by.dorogokupets.kidshop.repository.CandidateRepository;
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
  public Page<User> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
    Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    return candidateRepository.findAll(pageable);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void save(CandidateDto candidateDto) throws ServiceException {
    User user = candidateMapper.mapToCandidate(candidateDto);

    FileDB cvFileDb = convertToFileDB(candidateDto.getCvFile());
    FileDB imageFileDb = convertToFileDB(candidateDto.getPhoto());

    User savedUser = candidateRepository.save(user);

    cvFileDb.setUser(savedUser);
    imageFileDb.setUser(savedUser);

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
    Optional<User> candidate = candidateRepository.findById(id);
    return candidate.map(candidateMapper::mapToCandidateDTO).orElse(null);
  }

  @Override
  public User findCandidateById(Long candidateId) {
    return candidateRepository.findById(candidateId).orElse(null);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void update(CandidateDto candidateDto) throws ServiceException {
    User currentUser = candidateRepository.getReferenceById(candidateDto.getCandidateId());
    currentUser.setDescription(candidateDto.getDescription());
    currentUser.setPatronymic(candidateDto.getPatronymic());
    currentUser.setFirstName(candidateDto.getFirstName());
    currentUser.setLastName(candidateDto.getLastName());
    currentUser.setDirection(candidateDto.getDirection());
    FileDB cvFile = filesRepository.findByCandidateAndContentType(currentUser, MediaType.APPLICATION_PDF_VALUE);
    FileDB image = filesRepository.findByCandidateAndContentType(currentUser, MediaType.IMAGE_PNG_VALUE);

    MultipartFile cvFileToUpdate = candidateDto.getCvFile();
    MultipartFile imageFileToUpdate = candidateDto.getPhoto();

    if (cvFileToUpdate != null) {
      if (cvFile == null) {
        cvFile = convertToFileDB(cvFileToUpdate);
        cvFile.setUser(currentUser);
      } else {
        updateFileDB(cvFile, cvFileToUpdate);
      }
      filesRepository.save(cvFile);
    }

    if (imageFileToUpdate != null) {
      if (image == null) {
        image = convertToFileDB(imageFileToUpdate);
        image.setUser(currentUser);
      } else {
        updateFileDB(image, imageFileToUpdate);
      }
      filesRepository.save(image);
    }
    candidateRepository.save(currentUser);
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
