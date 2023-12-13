package by.dorogokupets.kidshop.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Table(name = "candidate_test_results")
public class TestResult {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_result_sequence")
  @SequenceGenerator(name = "test_result_sequence", sequenceName = "test_result_sequence", allocationSize = 1, initialValue = 1)
//  @Column(name = "id_test_result", nullable = false, unique = true)
  private Long testResultId;
//  @Column(name = "candidate_id")
//  private int candidateId;
//  @Column(name = "test_id")
//  private int testId;
//  @DateTimeFormat(pattern="yyyy-MM-dd")
//  @Column(name = "date")
//  private LocalDate date;
//  @Column(name = "mark")
//  private int mark;

}
