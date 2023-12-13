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
@Table(name = "tests")
public class Test {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_sequence")
  @SequenceGenerator(name = "test_sequence", sequenceName = "test_sequence", allocationSize = 1, initialValue = 1)
  @Column(name = "id_test", nullable = false, unique = true)
  private long testId;
  @Column(name = "title")
  private String title;
  @Column(name = "description")
  private String description;
  @Column(name = "direction")
  private Long direction;
}
