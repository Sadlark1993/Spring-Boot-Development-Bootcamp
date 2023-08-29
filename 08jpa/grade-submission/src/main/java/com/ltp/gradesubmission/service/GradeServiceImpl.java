package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.GradeNotFoundException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {

  //I don't need to @Autowired this cuz I'm using @AllArgsConstructor that will create a constructor,
  //the spring automatically injects the beans into the arguments of the constructor.
  //I will not do it to every service and controller class cuz its too much for my head.
  GradeRepository gradeRepository;
  StudentRepository studentRepository;
  CourseRepository courseRepository;

  @Override
  public Grade getGrade(Long studentId, Long courseId) {
    Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
    if (grade.isPresent()) {
      return grade.get();
    }
    throw new GradeNotFoundException(studentId, courseId);
  }

  @Override
  public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
    Student student = studentRepository.findById(studentId).get();
    Course course = courseRepository.findById(courseId).get();
    grade.setStudent(student);
    grade.setCourse(course);
    return gradeRepository.save(grade);
  }

  @Override
  public Grade updateGrade(String score, Long studentId, Long courseId) {
    Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
    if (grade.isPresent()) {
      Grade grade2 = grade.get();
      grade2.setScore(score);
      return gradeRepository.save(grade2);
    }
    throw new GradeNotFoundException(studentId, courseId);
  }

  @Override
  public void deleteGrade(Long studentId, Long courseId) {}

  @Override
  public List<Grade> getStudentGrades(Long studentId) {
    return null;
  }

  @Override
  public List<Grade> getCourseGrades(Long courseId) {
    return null;
  }

  @Override
  public List<Grade> getAllGrades() {
    return null;
  }
}
