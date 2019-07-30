package com.iktpreobuka.zavrsniProjekat.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class UserEntity {
	@Id
	@GeneratedValue
	private Integer id;
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
	

	@OneToMany(mappedBy="user",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	List<UserAccountEntity> userAccount=new ArrayList<UserAccountEntity>();

	
	public Integer getId() {
		return id;
	}
	public List<UserAccountEntity> getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(List<UserAccountEntity> userAccount) {
		this.userAccount = userAccount;
	}
	public void setId(Integer id) {
		this.id = id;
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



	public UserEntity(String name, String surname, String jmbg, String email, String phoneNumber) {
		super();
		
		this.name = name;
		this.surname = surname;
		this.jmbg = jmbg;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}






	public UserEntity() {
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
	
	

}
