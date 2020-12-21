package com.mars.assignment.controller;
  

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mars.assignment.exception.PersonNotFoundException;
import com.mars.assignment.model.Person;
import com.mars.assignment.repository.PersonRepository;

@RestController
@RequestMapping("/restapi")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;

	@PostMapping("/addPerson")
	public Person addPerson(@RequestBody Person person) {
		return personRepository.save(person);
	}

	@GetMapping("/listPerson")
	public List<Person> listPerson() {
		return  personRepository.findAll();
	}

	@GetMapping("/getPerson/{id}")
	public Person getPerson(@PathVariable(value = "id") Long id) {

		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));		  
		return person;
	}
	
	@PutMapping("/editPerson/{id}")
	public Person editPerson(@PathVariable(value = "id") Long id, @RequestBody Person personDetails) {

		Person person = personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));

		person.setFirstName(personDetails.getFirstName());
		person.setLastName(personDetails.getLastName());

		Person updatedPerson = personRepository.save(person);
		return updatedPerson;
	}

	@DeleteMapping("/deletePerson/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));

		personRepository.delete(person);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/countPerson")
	public String  countPerson() {
		return "Total Number of Person: "+personRepository.count();
	}

}
