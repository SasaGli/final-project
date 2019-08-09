package com.iktpreobuka.zavrsniProjekat.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.zavrsniProjekat.controllers.util.RESTError;
import com.iktpreobuka.zavrsniProjekat.entities.ParentEntity;
import com.iktpreobuka.zavrsniProjekat.repositories.ParentRepository;


@RestController
@RequestMapping("/parent")
public class ParentController {
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired ParentRepository parentRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAllParents()
	
	{
		return new ResponseEntity<Iterable<ParentEntity>>(parentRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		if (parentRepository.findById(id).isPresent()) {
			return new ResponseEntity<ParentEntity>(parentRepository.findById(id).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("Teacher with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
}
	
}
