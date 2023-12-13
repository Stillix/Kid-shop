package by.dorogokupets.kidshop.domain.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResultDto {
  private Long testResultId;
  private int candidateId;
  private int testId;
  private LocalDate date;
  private int mark;

}
