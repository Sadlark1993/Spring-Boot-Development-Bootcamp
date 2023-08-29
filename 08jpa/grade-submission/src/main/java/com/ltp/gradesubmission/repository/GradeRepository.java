package com.ltp.gradesubmission.repository;

import com.ltp.gradesubmission.entity.Grade;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface GradeRepository extends CrudRepository<Grade, Long> {
  //you need to follow a very strict criteria to declare abstract methods that spring boot will implement
  Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
