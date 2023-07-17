package com.readmultiplefiles.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.readmultiplefiles.app.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

}
