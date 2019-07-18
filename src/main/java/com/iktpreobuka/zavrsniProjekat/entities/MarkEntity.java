package com.iktpreobuka.zavrsniProjekat.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class MarkEntity {
	@Id
	@GeneratedValue
	private Integer id;
	private LocalDate date;
	private Integer mark;
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="student")
	private StudentEntity student;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="subject")
	private SubjectEntity subject;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
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
