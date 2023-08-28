package com.ltp.gradesubmission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {
        //printGrades(studentRepository.findById(id).get());
        return studentRepository.findById(id).get();
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
        return (List<Student>)studentRepository.findAll();
    }

    //just to show that the student object have access to its grades
/*     void printGrades(Student student){
        for(Grade grade : student.getGrades()){
            System.out.println(grade.getScore());
        }
    } */
}