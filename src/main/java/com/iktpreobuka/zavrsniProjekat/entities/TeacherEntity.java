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

public class TeacherEntity extends UserEntity {
	@Id
	
	private Integer id;
	
	
	@OneToMany(mappedBy="teacher",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	List<MarkEntity> mark=new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinTable(name="Subject_Teacher",joinColumns= {@JoinColumn(name="Teacher_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Subject_id",nullable=false)})
	Set <GradeEntity> subjects=new HashSet<GradeEntity>();
	
	
	
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
