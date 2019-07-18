package com.iktpreobuka.zavrsniProjekat.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.iktpreobuka.zavrsniProjekat.enumerations.ERole;

@Entity
public class UserAccountEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private String username;
	private String password;
	private ERole role;
	
	
	public UserAccountEntity() {
		super();
	}


	public UserAccountEntity(Integer id, String username, String password, ERole role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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
