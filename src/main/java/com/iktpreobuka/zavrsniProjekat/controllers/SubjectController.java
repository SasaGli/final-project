package com.iktpreobuka.zavrsniProjekat.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.zavrsniProjekat.controllers.util.RESTError;
import com.iktpreobuka.zavrsniProjekat.entities.SubjectEntity;
import com.iktpreobuka.zavrsniProjekat.entities.TeacherEntity;
import com.iktpreobuka.zavrsniProjekat.repositories.SubjectRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.TeacherRepository;



@RestController
@RequestMapping("/subject")
public class SubjectController {
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
@Autowired SubjectRepository subjectRepository;
@Autowired TeacherRepository teacherRepository;
@Secured("ROLE_ADMIN")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAllSubjects()
	
	{
		return new ResponseEntity<Iterable<SubjectEntity>>(subjectRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		if (subjectRepository.findById(id).isPresent()) {
			return new ResponseEntity<SubjectEntity>(subjectRepository.findById(id).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("Subject with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}

	
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(method=RequestMethod.POST,value="/addSubject")
	public ResponseEntity<?> addSubject (@Valid @RequestBody SubjectEntity newSubject,BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
		}
		SubjectEntity subject = new SubjectEntity();
		subject.setNumberOfClassesPerWeek(newSubject.getNumberOfClassesPerWeek());
		subject.setName(newSubject.getName());
		subjectRepository.save(subject);
		logger.info("subject added");
		return new ResponseEntity<SubjectEntity>(subject,HttpStatus.OK);
		
		
		
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(method=RequestMethod.PUT,value="/addTeacher/{teacherId}")
	public ResponseEntity<?>addTeacher(@PathVariable Integer teacherId,@RequestParam Integer subjectId)
	{
		TeacherEntity teacher=new TeacherEntity();
		SubjectEntity subject=new SubjectEntity();
		subject=subjectRepository.findById(subjectId).orElse(null);
		teacher=teacherRepository.findById(teacherId).orElse(null);
		if(subject==null)
		{
			return new ResponseEntity<>(new RESTError("Subject doesn't exists"),HttpStatus.BAD_REQUEST);
		}
		
		teacher=teacherRepository.findById(teacherId).orElse(null);
		if(teacher==null)
		{
			return new ResponseEntity<>(new RESTError("teacher doesn't exists"),HttpStatus.BAD_REQUEST);
		}
		subject.getTeachers().add(teacher);
		subjectRepository.save(subject);
		logger.info("Teacher added");
		return new ResponseEntity<SubjectEntity>(subject,HttpStatus.OK);
		
	}
	
private String createErrorMessage(BindingResult result)
{
	return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
}
}
