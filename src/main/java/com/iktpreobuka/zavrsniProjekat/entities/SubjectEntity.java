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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class SubjectEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	@NotNull(message="Name must be provided")
	@Size(min=2,max=30,message="Name must be between {min} and {max} characters long.")
	private String name;
	@Min(value=1,message="Number of classes must be higher than 0")
	@Max(value=5,message="Number of classes must be Lower or equal to 10")
	private Integer numberOfClassesPerWeek;
	@JsonManagedReference(value="subject-mark")
	@OneToMany(mappedBy="subject",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	List<MarkEntity> mark =new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="Subject_Grade",joinColumns= {@JoinColumn(name="Subject_id")},
	inverseJoinColumns= {@JoinColumn(name="Grade_id")})
	List <GradeEntity> grades=new ArrayList<GradeEntity>();
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="Subject_Teacher",joinColumns= {@JoinColumn(name="Subject_id")},
	inverseJoinColumns= {@JoinColumn(name="Teacher_id")})
	List <TeacherEntity> teachers=new ArrayList<TeacherEntity>();
	
	public SubjectEntity() {
		super();
	}

	public SubjectEntity(Integer id,
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be between {min} and {max} characters long.") String name,
			@Min(value = 1, message = "Number of classes must be higher than 0") @Max(value = 5, message = "Number of classes must be Lower or equal to 10") Integer numberOfClassesPerWeek,
			List<MarkEntity> mark, List<GradeEntity> grades, List<TeacherEntity> teachers) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfClassesPerWeek = numberOfClassesPerWeek;
		this.mark = mark;
		this.grades = grades;
		this.teachers = teachers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfClassesPerWeek() {
		return numberOfClassesPerWeek;
	}

	public void setNumberOfClassesPerWeek(Integer numberOfClassesPerWeek) {
		this.numberOfClassesPerWeek = numberOfClassesPerWeek;
	}

	public List<MarkEntity> getMark() {
		return mark;
	}

	public void setMark(List<MarkEntity> mark) {
		this.mark = mark;
	}

	public List<GradeEntity> getGrades() {
		return grades;
	}

	public void setGrades(List<GradeEntity> grades) {
		this.grades = grades;
	}

	public List<TeacherEntity> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherEntity> teachers) {
		this.teachers = teachers;
	}
	
	


	

}
