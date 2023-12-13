package by.dorogokupets.cvservice.mapper;


import by.dorogokupets.cvservice.domain.dto.CandidateDto;
import by.dorogokupets.cvservice.domain.model.Candidate;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CandidateMapper {

  CandidateDto mapToCandidateDTO(Candidate candidate);

  Candidate mapToCandidate(CandidateDto candidateDto);

}
