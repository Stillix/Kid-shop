package by.dorogokupets.kidshop.domain.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {
  private Long candidateId;
  private String firstName;
  private String lastName;
  private String patronymic;
  private String description;
  private MultipartFile  cvFile;
  private MultipartFile photo;
  private Long direction;
}
