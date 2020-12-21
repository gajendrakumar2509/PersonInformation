package com.mars.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mars.assignment.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
