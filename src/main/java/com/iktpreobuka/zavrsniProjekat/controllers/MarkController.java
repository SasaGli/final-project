package com.iktpreobuka.zavrsniProjekat.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.zavrsniProjekat.controllers.util.RESTError;
import com.iktpreobuka.zavrsniProjekat.entities.MarkEntity;
import com.iktpreobuka.zavrsniProjekat.entities.UserEntity;
import com.iktpreobuka.zavrsniProjekat.entities.dto.MarkDTO;
import com.iktpreobuka.zavrsniProjekat.repositories.MarkRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.StudentRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.SubjectRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.TeacherRepository;
import com.iktpreobuka.zavrsniProjekat.services.SubjectTeacherDAO;

@RestController
@RequestMapping("/mark")
public class MarkController {
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired MarkRepository markRepository;
	@Autowired StudentRepository studentRepository;
	@Autowired TeacherRepository teacherRepository;
	@Autowired SubjectRepository subjectRepository;
	@Autowired SubjectTeacherDAO subjectTeacherDAO;
	
	private String createErrorMessage (BindingResult result)
	{
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/addMark/{studentId}")
	public ResponseEntity<?> addMark(@Valid @RequestBody MarkDTO newMark,BindingResult result,@PathVariable Integer studentId,@RequestParam Integer subjectId,@RequestParam Integer teacherId)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
		}
	
	         //  Integer userId= ((UserEntity) principal).getId();
	       
		 
		MarkEntity mark=new MarkEntity();
		mark.setMark(newMark.getMark());
		mark.setDate(LocalDate.now());
		mark.setStudent(studentRepository.findById(studentId).orElse(null));
		mark.setTeacher(teacherRepository.findById(teacherId).orElse(null));
		mark.setSubject(subjectRepository.findById(subjectId).orElse(null));
		//mark.setSubject(subjectTeacherDAO.findSubjectByTeacherId(subjectId));
		markRepository.save(mark);
		logger.info("mark added");
		return new ResponseEntity<MarkEntity>(mark,HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/deleteMark/{id}")
	public ResponseEntity<?> deleteMark(@PathVariable Integer id)
	{
		//Integer userId= ((UserEntity) principal).getId();
		MarkEntity mark=new MarkEntity();
		mark=markRepository.findById(id).orElse(null);
		if (mark==null)
		{
			return new ResponseEntity<>(new RESTError("Mark doesn't exists"),HttpStatus.BAD_REQUEST);
		}
		/*
		 * if(mark.getTeacher().equals(teacherRepository.findById(userId))) { return new
		 * ResponseEntity<>(new
		 * RESTError("Can't delete other teacher mark"),HttpStatus.BAD_REQUEST); }
		 */
		markRepository.delete(mark);
		logger.info("mark deleted");
		return new ResponseEntity<MarkEntity>(mark,HttpStatus.OK);
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(method=RequestMethod.POST,value="/admin/addMark/{id}")
	public ResponseEntity<?> addAMark(@Valid @RequestBody MarkDTO newMark,BindingResult result,@PathVariable Integer id,@RequestParam Integer idTeacher,@RequestParam Integer idSubject)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
		}
		
	           
	       
		 
		MarkEntity mark=new MarkEntity();
		mark.setMark(newMark.getMark());
		mark.setDate(LocalDate.now());
		mark.setStudent(studentRepository.findById(id).orElse(null));
		mark.setTeacher(teacherRepository.findById(idTeacher).orElse(null));
		mark.setSubject(subjectRepository.findById(idSubject).orElse(null));
		markRepository.save(mark);
		logger.info("mark added");
		return new ResponseEntity<MarkEntity>(mark,HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/admin/deleteMark/{id}")
	public ResponseEntity<?> deleteAMark(@PathVariable Integer id)
	{
		
		MarkEntity mark=new MarkEntity();
		mark=markRepository.findById(id).orElse(null);
		if (mark==null)
		{
			return new ResponseEntity<>(new RESTError("Mark doesn't exists"),HttpStatus.BAD_REQUEST);
		}
	
		markRepository.delete(mark);
		return new ResponseEntity<MarkEntity>(mark,HttpStatus.OK);
	}
}
