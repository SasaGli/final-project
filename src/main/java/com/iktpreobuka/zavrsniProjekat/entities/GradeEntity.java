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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class GradeEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer grade;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="Subject_Grade",joinColumns= {@JoinColumn(name="Grade_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Subject_id",nullable=false)})
	List <SubjectEntity> subjects=new ArrayList<SubjectEntity>();
	
	
	
	public List<ClassEntity> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassEntity> classes) {
		this.classes = classes;
	}

	public List<SubjectEntity> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectEntity> subjects) {
		this.subjects = subjects;
	}

	@JsonManagedReference(value="class-grade")
	@OneToMany(mappedBy="grade",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<ClassEntity>classes=new ArrayList<ClassEntity>();
	public GradeEntity() {
		super();
	}

	public GradeEntity(Integer id, Integer grade) {
		super();
		this.id = id;
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	

}
