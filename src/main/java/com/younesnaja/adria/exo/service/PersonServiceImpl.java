package com.younesnaja.adria.exo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.younesnaja.adria.exo.dao.PersonDAO;
import com.younesnaja.adria.exo.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDAO personDAO;

	@Override
	public Person getPerson(Long id) {
		return personDAO.getPerson(id);
	}
	
	@Override
	public void savePerson(Person person) {
		try {
			personDAO.savePerson(person);
		} catch (IOException e) {
			System.err.println("IOException : " + e.getMessage());
		}
	}
	
	@Override
	public void deletePerson(Long id) {
		personDAO.deletePerson(id);
	}

	@Override
	public List<Person> getAllPersons() {
		return personDAO.getAllPersons();
	}
}
