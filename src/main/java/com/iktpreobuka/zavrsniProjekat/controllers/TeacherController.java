package com.iktpreobuka.zavrsniProjekat.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.iktpreobuka.zavrsniProjekat.controllers.util.RESTError;
import com.iktpreobuka.zavrsniProjekat.entities.TeacherEntity;
import com.iktpreobuka.zavrsniProjekat.repositories.TeacherRepository;



@RestController
@RequestMapping("/teacher")
public class TeacherController {
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired TeacherRepository teacherRepository;
	@Secured("ROLE_ADMIN")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAllTeachers()
	
	{
		return new ResponseEntity<Iterable<TeacherEntity>>(teacherRepository.findAll(), HttpStatus.OK);
	}
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		if (teacherRepository.findById(id).isPresent()) {
			return new ResponseEntity<TeacherEntity>(teacherRepository.findById(id).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("Teacher with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}

	
	}
}
