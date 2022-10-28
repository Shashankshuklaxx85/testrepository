package com.example.springbootexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootexample.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}