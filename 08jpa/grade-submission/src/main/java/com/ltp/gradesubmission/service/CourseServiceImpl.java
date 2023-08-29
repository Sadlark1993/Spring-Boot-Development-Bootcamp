package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.exception.CourseNotFoundException;
import com.ltp.gradesubmission.repository.CourseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

  @Autowired
  CourseRepository courseRepository;

  @Override
  public Course getCourse(Long id) {
    Optional<Course> course = courseRepository.findById(id);
    if (course.isPresent()) {
      return course.get();
    }
    throw new CourseNotFoundException(id);
  }

  @Override
  public Course saveCourse(Course course) {
    return courseRepository.save(course);
  }

  @Override
  public void deleteCourse(Long id) {
    courseRepository.deleteById(id);
  }

  @Override
  public List<Course> getCourses() {
    return (List<Course>) courseRepository.findAll();
  }
}
