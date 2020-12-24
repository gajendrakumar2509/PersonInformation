package com.mars.assignment.controller;
  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String  addPerson(@RequestBody Person person) {		
		Person addedPerson = personRepository.save(person);		
		if(addedPerson!=null && addedPerson.getId()>0){
			return  "Person details added successfully.";
		}		
		return  "Filed to add person details.";		
	}
	
	@GetMapping("/listPerson")
	public String listPerson() {
		List<Person> personList =  personRepository.findAll();
		if(personList.isEmpty()){
			 return "No records found.";
		}
		return personList.toString();		
	}
 	
	@GetMapping(value ="/getPerson/{id}")	
	public Person getPerson(@PathVariable(value = "id") Long id ) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));		  
		return person;
	}
	
	@PutMapping("/editPerson/{id}")
	public String editPerson(@PathVariable(value = "id") Long id, @RequestBody Person personDetails)  {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		person.setFirstName(personDetails.getFirstName());
		person.setLastName(personDetails.getLastName());
		person.setAddressLine1(personDetails.getAddressLine1());
		person.setAddressLine2(personDetails.getAddressLine2()); 
		Person updatedPerson = personRepository.save(person);
		if(updatedPerson!=null  && updatedPerson.getId() >0 ){
			return "Person details updated successfully.";
		}
		return "Filed to update person details.";				 
	}

	@DeleteMapping("/deletePerson/{id}")
	public String deletePerson(@PathVariable(value = "id") Long id) {		
		Person findPerson = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));		 
		personRepository.delete(findPerson);
		return "Person deleted successfully";		 		 
	}

	@GetMapping("/countPerson")
	public String  countPerson() {
		long personCount = personRepository.count();
		if(personCount>0){
			return "Total Number of Person: "+personCount;
		}
		return "No records found";
	}

}
