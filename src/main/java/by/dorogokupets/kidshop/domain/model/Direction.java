package by.dorogokupets.kidshop.domain.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "directions")
public class Direction {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "direction_sequence")
  @SequenceGenerator(name = "direction_sequence", sequenceName = "direction_sequence", allocationSize = 1, initialValue = 1)
  @Column(name = "id_direction", nullable = false, unique = true)
  private Long directionId;
  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;

}
