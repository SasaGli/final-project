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

@Entity
public class GradeEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer grade;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinTable(name="Subject_Grade",joinColumns= {@JoinColumn(name="Grade_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Subject_id",nullable=false)})
	Set <GradeEntity> subjects=new HashSet<GradeEntity>();
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinTable(name="Class_Grade",joinColumns= {@JoinColumn(name="Grade_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Class_id",nullable=false)})
	Set <GradeEntity> classes=new HashSet<GradeEntity>();
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
