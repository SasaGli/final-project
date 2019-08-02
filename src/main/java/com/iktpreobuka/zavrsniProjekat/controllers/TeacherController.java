package com.iktpreobuka.zavrsniProjekat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@Autowired TeacherRepository teacherRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAllTeachers()
	
	{
		return new ResponseEntity<Iterable<TeacherEntity>>(teacherRepository.findAll(), HttpStatus.OK);
	}
	
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
