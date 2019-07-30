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
@Entity

public class StudentEntity extends UserEntity{
	
	@Id
	
	private Integer id;
	
	@OneToMany(mappedBy="student",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	List<MarkEntity> mark=new ArrayList<>();
	
	@OneToMany(mappedBy="studentClass",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	List<StudentEntity> studentClass=new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinTable(name="Parent_Student",joinColumns= {@JoinColumn(name="Student_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Parent_id",nullable=false)})
	Set <GradeEntity> parents=new HashSet<GradeEntity>();
	
	

	public StudentEntity(String name, String surname, String jmbg, String email, String phoneNumber) {
		super(name, surname, jmbg, email, phoneNumber);
	}


	public StudentEntity() {
		
		super();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	
	

}
