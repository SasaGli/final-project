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
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.zavrsniProjekat.controllers.util.RESTError;
import com.iktpreobuka.zavrsniProjekat.entities.ParentEntity;
import com.iktpreobuka.zavrsniProjekat.entities.StudentEntity;
import com.iktpreobuka.zavrsniProjekat.repositories.ParentRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.StudentRepository;


@RestController
@RequestMapping("/student")
public class StudentController {
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired StudentRepository studentRepository;
	@Autowired ParentRepository parentRepository;
	@RequestMapping(method=RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> getAllStudents()
	
	{
		return new ResponseEntity<Iterable<StudentEntity>>(studentRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		if (studentRepository.findById(id).isPresent()) {
			return new ResponseEntity<StudentEntity>(studentRepository.findById(id).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("Student with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
}
	@Secured("ROLE_ADMIN")
	@RequestMapping(method=RequestMethod.PUT ,value="/addParent{idParent}/{id}")
	public ResponseEntity<?> addParent(@PathVariable Integer idParent,@PathVariable Integer id)
	{
		
		StudentEntity student =new StudentEntity();
		ParentEntity parent = new ParentEntity();
		student=studentRepository.findById(id).orElse(null);
		parent=parentRepository.findById(idParent).orElse(null);
		if(student==null)
		{
			return new ResponseEntity<RESTError>(new RESTError("Student doesn't exists"),HttpStatus.BAD_REQUEST);
		}
		if(parent==null)
		{
			return new ResponseEntity<RESTError>(new RESTError("Parent doesn't exists"),HttpStatus.BAD_REQUEST);
		}
			student.getParents().add(parent);
			studentRepository.save(student);
			logger.info("parent added");
			return new ResponseEntity<StudentEntity>(student,HttpStatus.OK);
		
	}
	private String createErrorMessage (BindingResult result)
	{
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining (" "));
	}
}
