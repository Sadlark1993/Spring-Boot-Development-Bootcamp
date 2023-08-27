package com.ltp.gradesubmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.ltp.gradesubmission.entity.Student;

//the first parameter is the type of the entity, the second one is the type of its primary key
//so, we can @Autowired the StudentRepository Bean from StudentServiceImpl
public interface StudentRepository extends CrudRepository<Student, Long> {

}