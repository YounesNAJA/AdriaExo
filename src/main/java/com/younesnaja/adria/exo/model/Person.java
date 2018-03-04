package com.younesnaja.adria.exo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Person implements Serializable {
	private static final long serialVersionUID = -6061836083473255990L;
	public static final String SEPARATOR = " | ";
	public static final String GET_SEPARATOR = " \\| ";
	public static final String LINE_SEPARATOR = "\n";
	
	@Id
	private Long id;
	
	@NotEmpty(message="Entez votre nom complet.")
	@Pattern(regexp="^[a-zA-Z]+((['. -][a-zA-Z ])+[a-zA-Z]*)*$", message="Nom erroné.")
	private String completeName;
	
	@NotEmpty(message="Entez votre numéro de téléphone.")
	@Size(min=10,max=10, message="Le numéro de téléphone doit contenir 10 chiffres.")
	@Pattern(regexp="(^$|[0-9]{10})", message="Le numéro de téléphone est invalide.")
	private String phoneNum;
	
	@NotEmpty(message="Entez votre email.")
	@Pattern(regexp = "^\\S+@\\S+\\.com$", message = "Adresse email invalide.")
	private String email;

	public Person() {
	}

	public Person(Long id, String completeName, String phoneNum, String email) {
		super();
		this.id = id;
		this.completeName = completeName;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	public Person(String line) {
		String[] parts = line.split(GET_SEPARATOR);
		this.id = Long.parseLong(parts[0]);
		this.completeName = parts[1];
		this.phoneNum = parts[2];
		this.email = parts[3];
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return id + SEPARATOR + completeName + SEPARATOR + phoneNum + SEPARATOR + email;
	}
	
	
}
