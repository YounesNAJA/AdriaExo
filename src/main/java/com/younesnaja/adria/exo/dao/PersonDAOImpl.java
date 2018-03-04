package com.younesnaja.adria.exo.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.younesnaja.adria.exo.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {
	private String fileName = "/home/younes/git/adria/src/main/webapp/resources/Persons.txt";
	
	@Override
	public void changeFileName(String fileName) {
		this.fileName = "/home/younes/git/adria/src/main/webapp/resources/" + fileName;
	}
	
	@Override
	public void clearFileContent() {
		this.emptyFile();
	}

	@Override
	public void savePerson(Person person) {
		person.setId(System.currentTimeMillis());
		this.writeToFile(person);
	}

	@Override
	public Person getPerson(Long id) {
		try (BufferedReader br = new BufferedReader(new FileReader(this.getFile()))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	
				String[] parts = line.split(Person.SEPARATOR);
				Long idFound = Long.parseLong(parts[0]);
				if(id.equals(idFound))
					return new Person(line);
				return null;
		    }
		} catch (FileNotFoundException e) {
			System.err.println("Fichier non trouvé. " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException. " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Person> getAllPersons() {
		return this.readFromFile();
	}
	
	private File getFile() {
		File file = new File(fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	private File emptyFile() {
		this.getFile().delete();
		return this.getFile();
	}
	
	private void writeToFile(Person person) {
		try {
			FileWriter fw = new FileWriter(this.getFile(), true);
			fw.write(person.toString() + Person.LINE_SEPARATOR);
			fw.close();
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	private List<Person> readFromFile() {
		List<Person> persons = new ArrayList<Person>();
		try (BufferedReader br = new BufferedReader(new FileReader(this.getFile()))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	persons.add(new Person(line));
		    }
		} catch (FileNotFoundException e) {
			System.err.println("Fichier non trouvé. " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException. " + e.getMessage());
		}
		
	    return persons;
	}
	
	public void deletePerson(Long id) {
		List<Person> persons = new ArrayList<Person>();
		
		// TODO change with getPerson method later
		try (BufferedReader br = new BufferedReader(new FileReader(this.getFile()))) {
		    String line;
		    while ((line = br.readLine()) != null) {
				String[] parts = line.split(Person.SEPARATOR);
				Long idFound = Long.parseLong(parts[0]);
				if(!id.equals(idFound))
					persons.add(new Person(line));
		    }
		} catch (FileNotFoundException e) {
			System.err.println("Fichier non trouvé. " + e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException. " + e.getMessage());
		}
		
		this.emptyFile();
		
		for (Person person : persons) {
			this.writeToFile(person);
		}
	}
}
