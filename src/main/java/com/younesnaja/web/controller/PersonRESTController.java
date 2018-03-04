package com.younesnaja.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.younesnaja.adria.exo.model.Person;
import com.younesnaja.adria.exo.model.PersonJSON;
import com.younesnaja.adria.exo.service.PersonService;

@RestController
public class PersonRESTController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getAllPersons() {
		List<Person> persons = new ArrayList<Person>();
		persons = personService.getAllPersons();

		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}

	@RequestMapping(value = "/addPerson", method = RequestMethod.POST)
	public PersonJSON addPerson(@Valid Person person, BindingResult bindingResult) {
		PersonJSON response = new PersonJSON();

		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<String, String>();
			List<FieldError> errorsList = bindingResult.getFieldErrors();
			for (FieldError error : errorsList) {
				errors.put(error.getField(), error.getDefaultMessage());
			}

			response.setValidated(false);
			response.setErrorMessages(errors);
		} else {
			personService.savePerson(person);
			response.setValidated(true);
			response.setPerson(person);
		}

		return response;
	}

	@RequestMapping(value = "/deletePerson/{id}", method = RequestMethod.DELETE)
	public void deletePerson(@PathVariable("id") Long id) {
		personService.deletePerson(id);
	}
}
