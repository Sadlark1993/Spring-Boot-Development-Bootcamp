package com.ltp.gradesubmission.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
  name = "grade",
  uniqueConstraints = { //these are arrays, not obj!! It's Java, not JavaScript!!
    @UniqueConstraint(columnNames = { "student_id", "course_id" }), //these names must match the column names
  }
)
public class Grade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "score", nullable = false)
  private String score;

  //this is how we create a foreign key in spring-boot
  @ManyToOne(optional = false) //many grades belong to one student
  @JoinColumn(name = "student_id", referencedColumnName = "id")
  private Student student;

  @ManyToOne(optional = false)
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Course course;
}
