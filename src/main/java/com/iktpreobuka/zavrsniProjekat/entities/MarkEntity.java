package com.iktpreobuka.zavrsniProjekat.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
public class MarkEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private LocalDate date;
	@Min(value=1,message="Mark must be higher than 0")
	@Max(value=5,message="Mark must be Lower or equal to 5")
	private Integer mark;
	@JsonBackReference(value="mark-student")
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="student")
	private StudentEntity student;
	@JsonBackReference(value="subject-mark")
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="subject")
	private SubjectEntity subject;
	@JsonBackReference(value="mark-teacher")
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="teacher")
	private TeacherEntity teacher;
	
	
	
	public MarkEntity() {
		super();
	}

	public MarkEntity(StudentEntity student, SubjectEntity subject, TeacherEntity teacher,Integer mark) {
		super();
		this.date = LocalDate.now();
		this.student = student;
		this.subject = subject;
		this.teacher = teacher;
		this.mark=mark;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}

	public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}
	
	
	

}
