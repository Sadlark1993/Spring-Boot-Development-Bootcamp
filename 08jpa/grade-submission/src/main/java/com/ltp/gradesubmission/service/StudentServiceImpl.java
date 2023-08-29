package com.ltp.gradesubmission.service;

//import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.StudentNotFoundException;
import com.ltp.gradesubmission.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  StudentRepository studentRepository;

  @Override
  public Student getStudent(Long id) {
    //printGrades(studentRepository.findById(id).get());
    Optional<Student> student = studentRepository.findById(id);
    return unwrapStudent(student, id);
  }

  @Override
  public Student saveStudent(Student student) {
    // Spring Boot implemented all the methods of CrudRepository in the StudentRepository Bean
    return studentRepository.save(student);
  }

  @Override
  public void deleteStudent(Long id) {
    studentRepository.deleteById(id);
  }

  @Override
  public List<Student> getStudents() {
    //return the list of students after parse it to List<Student>
    return (List<Student>) studentRepository.findAll();
  }

  static Student unwrapStudent(Optional<Student> entity, Long id) {
    if (entity.isPresent()) return entity.get(); else throw new StudentNotFoundException(id);
  }
  //just to show that the student object have access to its grades
  /*     void printGrades(Student student){
        for(Grade grade : student.getGrades()){
            System.out.println(grade.getScore());
        }
    } */
}
