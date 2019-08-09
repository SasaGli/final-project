package com.iktpreobuka.zavrsniProjekat.entities.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.iktpreobuka.zavrsniProjekat.enumerations.ERole;

public class UserDTO {
	@NotNull(message="First name must be provided")
	@Size(min=2,max=30,message="First name must be between {min} and {max} characters long.")
	private String name;
	@NotNull(message="Last name must be provided")
	@Size(min=2,max=30,message="Last name must be between {min} and {max} characters long.")
	private String surname;
	@NotNull(message="JMBG must be provided")
	@Size(min=13,max=13,message="JMBG must be 13 characters long.")
	private String jmbg;
	@NotNull(message="Email must be provided")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	message="Email is not valid.")
	private String email;
	@NotNull(message="Phone number must be provided")
	@Size(min=9,max=13,message="Phone number must be between {min} and {max} characters long.")
	private String phoneNumber;
	@NotNull(message="Username must be provided")
	@Size(min=5,max=15,message="Username must be between {min} and {max} characters long.")
	private String username;
	@NotNull(message="Password must be provided")
	@Size(min=5,max=10,message="Password must be between {min} and {max} characters long.")
	private String password;
	@NotNull(message="ROLE must be provided")
	private ERole role;
	public UserDTO(String name, String surname, String jmbg, String email, String phoneNumber, String username,
			String password,ERole role) {
		super();
		this.name = name;
		this.surname = surname;
		this.jmbg = jmbg;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public UserDTO() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ERole getRole() {
		return role;
	}
	public void setRole(ERole role) {
		this.role = role;
	}
	
	

}
