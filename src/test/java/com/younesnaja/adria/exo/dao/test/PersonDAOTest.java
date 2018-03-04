package com.younesnaja.adria.exo.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.younesnaja.adria.exo.dao.PersonDAO;
import com.younesnaja.adria.exo.model.Person;

@ContextConfiguration({ "/springTestConfig.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonDAOTest {

	@Autowired
	private PersonDAO personDao;
	private Person personTest;

	@Before
	public void setUp() {
		personDao.changeFileName("PersonsTest.txt");
		personDao.clearFileContent();
		personTest = new Person(15423L, "Richard Stallman", "0658749632", "test@test.com");
	}

	@Test
	public void testASavePerson() {
		try {
			personDao.savePerson(personTest);
			Long id = personTest.getId();
			assertEqualsPerson(personTest, personDao.getPerson(id));
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	@Test
	public void testBGetPerson() {
		try {
			personDao.savePerson(personTest);
			Long id = personTest.getId();
			assertEqualsPerson(personTest, personDao.getPerson(id));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEqualsPerson(personTest, personDao.getPerson(personTest.getId()));
	};

	@Test
	public void testCGetAllPersons() {
		try {
			personDao.savePerson(personTest);
			personDao.savePerson(personTest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(2, personDao.getAllPersons().size());
	};

	@Test
	public void testDDeletePerson() {
		try {
			personDao.savePerson(personTest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		personDao.deletePerson(personTest.getId());
		assertNull(personDao.getPerson(personTest.getId()));
	};

	private Boolean assertEqualsPerson(Person personA, Person personB) {
		Boolean id = personA.getId().equals(personB.getId());
		Boolean name = personA.getCompleteName().equals(personB.getCompleteName());
		Boolean email = personA.getEmail().equals(personB.getEmail());
		Boolean phone = personA.getPhoneNum().equals(personB.getPhoneNum());

		Boolean[] fields = { id, name, email, phone };

		for (Boolean field : fields) {
			if (field == Boolean.FALSE)
				return Boolean.FALSE;
		}
		return Boolean.TRUE;

	}

}
