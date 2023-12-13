package by.dorogokupets.kidshop.mapper;


import by.dorogokupets.kidshop.domain.dto.CandidateDto;
import by.dorogokupets.kidshop.domain.model.Candidate;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CandidateMapper {

  CandidateDto mapToCandidateDTO(Candidate candidate);

  Candidate mapToCandidate(CandidateDto candidateDto);

}
