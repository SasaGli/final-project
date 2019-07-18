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

@Entity
public class SubjectEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String subject;
	private Integer numberOfClassesPerWeek;
	
	@OneToMany(mappedBy="subject",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	List<MarkEntity> mark =new ArrayList<>();
	
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinTable(name="Subject_Grade",joinColumns= {@JoinColumn(name="Subject_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Grade_id",nullable=false)})
	Set <GradeEntity> grades=new HashSet<GradeEntity>();
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinTable(name="Subject_Teacher",joinColumns= {@JoinColumn(name="Subject_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Teacher_id",nullable=false)})
	Set <GradeEntity> teachers=new HashSet<GradeEntity>();
	
	
	public SubjectEntity() {
		super();
	}
	public SubjectEntity(Integer id, String subject, Integer numberOfClassesWeekly) {
		super();
		this.id = id;
		this.subject = subject;
		this.numberOfClassesPerWeek = numberOfClassesWeekly;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Integer getNumberOfClassesWeekly() {
		return numberOfClassesPerWeek;
	}
	public void setNumberOfClassesWeekly(Integer numberOfClassesWeekly) {
		this.numberOfClassesPerWeek = numberOfClassesWeekly;
	}
	
	

}
