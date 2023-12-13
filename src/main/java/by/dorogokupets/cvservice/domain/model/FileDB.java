package by.dorogokupets.cvservice.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "files")
public class FileDB {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_sequence")
  @SequenceGenerator(name = "file_sequence", sequenceName = "file_sequence", allocationSize = 1, initialValue = 1)
  @Column(name = "id_file", nullable = false, unique = true)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "candidate_id", nullable = false)
  private Candidate candidate;

  @Column
  private String name;

  @Column
  private String contentType;

  @Column
  private Long size;

  @Lob
  private byte[] data;

}
