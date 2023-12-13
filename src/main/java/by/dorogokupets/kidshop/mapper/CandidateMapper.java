package by.dorogokupets.kidshop.mapper;


import by.dorogokupets.kidshop.domain.dto.CandidateDto;
import by.dorogokupets.kidshop.domain.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CandidateMapper {

  CandidateDto mapToCandidateDTO(User user);

  User mapToCandidate(CandidateDto candidateDto);

}
