package com.iktpreobuka.zavrsniProjekat.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.zavrsniProjekat.controllers.util.RESTError;
import com.iktpreobuka.zavrsniProjekat.entities.GradeEntity;
import com.iktpreobuka.zavrsniProjekat.entities.SubjectEntity;
import com.iktpreobuka.zavrsniProjekat.repositories.GradeRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.SubjectRepository;


@RestController
@RequestMapping("/grade")
public class GradeController {
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired GradeRepository gradeRepository;
	@Autowired SubjectRepository subjectRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAllGrades()
	
	{
		return new ResponseEntity<Iterable<GradeEntity>>(gradeRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		if (gradeRepository.findById(id).isPresent()) {
			return new ResponseEntity<GradeEntity>(gradeRepository.findById(id).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("Teacher with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		
			
	}
	@RequestMapping(method=RequestMethod.POST,value="/addGrade")
	private ResponseEntity<?> addGrade(@Valid @RequestBody GradeEntity newGrade,BindingResult result)
	{
		
		
		
		if(result.hasErrors())
		{
			return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
		}
		GradeEntity grade =new GradeEntity();
		grade.setGrade(newGrade.getGrade());
		gradeRepository.save(grade);
		logger.info("Grade added");

			return new ResponseEntity<GradeEntity>(grade,HttpStatus.OK);
		
		
	}
	private String createErrorMessage(BindingResult result)
	{
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}
	@RequestMapping(method=RequestMethod.PUT,value="/addSubject/{gradeId}")
	private ResponseEntity<?> addSubject(@Valid @PathVariable Integer gradeId,@RequestParam Integer subjectId)
	{
		GradeEntity grade =new GradeEntity();
		grade=gradeRepository.findById(gradeId).orElse(null);
		SubjectEntity subject=new SubjectEntity();
		subject=subjectRepository.findById(subjectId).orElse(null);
		if(subject==null)
		{
			return new ResponseEntity<>(new RESTError("Subject doesn't exists"),HttpStatus.BAD_REQUEST);
		}
		if(grade==null)
		{
			return new ResponseEntity<>(new RESTError("Grade doesn't exists"),HttpStatus.BAD_REQUEST);
		}
		grade.getSubjects().add(subject);
		gradeRepository.save(grade);
		logger.info("subject added");

			return new ResponseEntity<GradeEntity>(grade,HttpStatus.OK);
		
		
	}
}
