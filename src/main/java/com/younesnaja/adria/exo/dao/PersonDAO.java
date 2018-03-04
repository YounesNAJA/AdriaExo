package com.younesnaja.adria.exo.dao;

import java.io.IOException;
import java.util.List;

import com.younesnaja.adria.exo.model.Person;

public interface PersonDAO {
	public void savePerson(Person person) throws IOException;
	public Person getPerson(Long id);
	public List<Person> getAllPersons();
	public void deletePerson(Long id);
	void changeFileName(String fileName);
	void clearFileContent();
}