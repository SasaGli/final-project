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
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class StudentEntity extends Person{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(mappedBy="student",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	List<MarkEntity> mark=new ArrayList<>();
	
	@OneToMany(mappedBy="studentClass",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	List<StudentEntity> studentClass=new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinTable(name="Parent_Student",joinColumns= {@JoinColumn(name="Student_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Parent_id",nullable=false)})
	Set <GradeEntity> parents=new HashSet<GradeEntity>();

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
