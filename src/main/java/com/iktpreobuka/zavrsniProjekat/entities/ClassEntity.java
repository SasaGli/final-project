package com.iktpreobuka.zavrsniProjekat.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ClassEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer classNumber;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinTable(name="Class_Grade",joinColumns= {@JoinColumn(name="Class_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Grade_id",nullable=false)})
	Set <GradeEntity> grades=new HashSet<GradeEntity>();
	
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="studentClass")
	private StudentEntity studentClass;
	public ClassEntity() {
		super();
	}

	public ClassEntity(Integer id, Integer classNumber) {
		super();
		this.id = id;
		this.classNumber = classNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(Integer classNumber) {
		this.classNumber = classNumber;
	}
	
	
	

}
