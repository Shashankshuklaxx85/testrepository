package com.example.springbootexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootexample.model.User;
import com.example.springbootexample.model.Userprofile;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
