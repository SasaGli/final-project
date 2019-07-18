package com.iktpreobuka.zavrsniProjekat.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ParentEntity extends Person {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String email;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinTable(name="Parent_Student",joinColumns= {@JoinColumn(name="Parent_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Student_id",nullable=false)})
	Set <GradeEntity> students=new HashSet<GradeEntity>();
	
	public ParentEntity() {
		super();
	}


	public ParentEntity(Integer id, String name, String surname, String email) {
		super(name,surname);
		this.id = id;
		
		this.email = email;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
