package com.iktpreobuka.zavrsniProjekat.entities;

import javax.persistence.Entity;

@Entity
public class AdminEntity extends UserEntity {
	private Integer id;

	public AdminEntity(String name, String surname, String jmbg, String email, String phoneNumber, Integer id) {
		super(name, surname, jmbg, email, phoneNumber);
		this.id = id;
	}

	public AdminEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

}
