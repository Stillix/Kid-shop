package by.dorogokupets.kidshop.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {
  private long testId;
  private String title;
  private String description;
  private Long direction;
}
