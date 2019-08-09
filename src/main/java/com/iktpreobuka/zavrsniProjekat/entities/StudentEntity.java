package com.iktpreobuka.zavrsniProjekat.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity

public class StudentEntity extends UserEntity{
	
	@Id
	
	private Integer id;
	@JsonManagedReference(value="mark-student")
	@OneToMany(mappedBy="student",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<MarkEntity> mark=new ArrayList<>();
	
	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="Parent_Student",joinColumns= {@JoinColumn(name="Student_id",nullable=false)},
	inverseJoinColumns= {@JoinColumn(name="Parent_id",nullable=false)})
	List <ParentEntity> parents=new ArrayList<ParentEntity>();
	@JsonBackReference(value="class-studentClass")
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="studentClass")
	private ClassEntity studentClass;
	

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


	public List<MarkEntity> getMark() {
		return mark;
	}


	public void setMark(List<MarkEntity> mark) {
		this.mark = mark;
	}





	public ClassEntity getStudentClass() {
		return studentClass;
	}


	public void setStudentClass(ClassEntity studentClass) {
		this.studentClass = studentClass;
	}


	public List<ParentEntity> getParents() {
		return parents;
	}


	public void setParents(List<ParentEntity> parents) {
		this.parents = parents;
	}




	
	
	
	
	
	

}
