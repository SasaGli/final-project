package com.iktpreobuka.zavrsniProjekat.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity

public class TeacherEntity extends UserEntity {
	@Id
	
	private Integer id;
	
	@JsonManagedReference(value="mark-teacher")
	@OneToMany(mappedBy="teacher",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<MarkEntity> mark=new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="Subject_Teacher",joinColumns= {@JoinColumn(name="Teacher_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Subject_id",nullable=false)})
	List <SubjectEntity> subjects=new ArrayList<SubjectEntity>();
	
	
	
	public TeacherEntity() {
		super();
	}


	public TeacherEntity(String name, String surname, String jmbg, String email, String phoneNumber) {
		super(name,surname, jmbg,email,phoneNumber);
		
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	
	
	

}
