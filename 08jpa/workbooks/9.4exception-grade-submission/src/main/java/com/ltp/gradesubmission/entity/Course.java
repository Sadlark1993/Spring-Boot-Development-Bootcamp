package com.ltp.gradesubmission.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank(message = "subject cannot be blank")
  @NonNull
  @Column(name = "subject", nullable = false)
  private String subject;

  @NotBlank(message = "code cannot be blank")
  @NonNull
  @Column(name = "code", nullable = false, unique = true)
  private String code;

  @NotBlank(message = "description cannot be blank")
  @NonNull
  @Column(name = "description", nullable = false)
  private String description;

  @JsonIgnore
  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
  private List<Grade> grades;
}
