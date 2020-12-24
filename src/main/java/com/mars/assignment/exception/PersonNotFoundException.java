package com.mars.assignment.exception;

public class PersonNotFoundException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	Long id=0l;
	public PersonNotFoundException(Long id) {
        super(String.format("Person Not Found for given Id %d : ", id));
        this.id= id;
    }
}
