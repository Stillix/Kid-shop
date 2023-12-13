package by.dorogokupets.cvservice.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionDto {

  private Long directionId;
  private String name;
  private String description;

}
