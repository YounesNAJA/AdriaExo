package com.younesnaja.adria.exo.service;

import java.util.List;

import com.younesnaja.adria.exo.model.Person;

public interface PersonService {

	Person getPerson(Long id);
	void savePerson(Person person);
	void deletePerson(Long id);
		
	List<Person> getAllPersons();

}
