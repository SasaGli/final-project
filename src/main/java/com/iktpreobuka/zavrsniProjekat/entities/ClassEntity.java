package com.iktpreobuka.zavrsniProjekat.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ClassEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer classNumber;
	@JsonBackReference(value="class-grade")
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="grade")
	private GradeEntity grade;
	
	
	
	
	public ClassEntity(Integer id, Integer classNumber, GradeEntity grade, List<StudentEntity> studentClass) {
		super();
		this.id = id;
		this.classNumber = classNumber;
		this.grade = grade;
		this.studentClass = studentClass;
	}

	@JsonManagedReference(value="class-studentClass")
	@OneToMany(mappedBy="studentClass",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<StudentEntity> studentClass=new ArrayList<StudentEntity>();
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

	public GradeEntity getGrade() {
		return grade;
	}

	public void setGrade(GradeEntity grade) {
		this.grade = grade;
	}


	public List<StudentEntity> getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(List<StudentEntity> studentClass) {
		this.studentClass = studentClass;
	}

	public void setClassNumber(Integer classNumber) {
		this.classNumber = classNumber;
	}
	
	
	

}
