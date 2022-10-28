package com.example.springbootexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootexample.model.Student;
import com.example.springbootexample.model.Userprofile;

@Repository
public interface UserprofileRepository extends JpaRepository<Userprofile, Long>{

}
