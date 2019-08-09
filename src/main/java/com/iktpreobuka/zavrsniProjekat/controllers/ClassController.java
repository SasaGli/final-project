package com.iktpreobuka.zavrsniProjekat.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.zavrsniProjekat.controllers.util.RESTError;
import com.iktpreobuka.zavrsniProjekat.entities.ClassEntity;
import com.iktpreobuka.zavrsniProjekat.entities.GradeEntity;
import com.iktpreobuka.zavrsniProjekat.entities.StudentEntity;
import com.iktpreobuka.zavrsniProjekat.repositories.ClassRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.GradeRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.StudentRepository;



@RestController
@RequestMapping("/class")
public class ClassController {
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired ClassRepository classRepository;
	@Autowired GradeRepository gradeRepository;
	@Autowired StudentRepository studentRepository;
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAllClasses()
	
	{
		return new ResponseEntity<Iterable<ClassEntity>>(classRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		if (classRepository.findById(id).isPresent()) {
			return new ResponseEntity<ClassEntity>(classRepository.findById(id).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("Class with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		
			
	}

	@RequestMapping(method=RequestMethod.POST,value="/addClass")
	private ResponseEntity<?> addClass(@Valid @RequestBody ClassEntity newClass,@RequestParam Integer gradeId)
	{
		ClassEntity addClass =new ClassEntity();
		addClass.setClassNumber(newClass.getClassNumber());
		GradeEntity grade=new GradeEntity();
		grade=gradeRepository.findById(gradeId).orElse(null);
		if(grade==null)
		{
			return new ResponseEntity<>(new RESTError("Grade doesn't exists"),HttpStatus.BAD_REQUEST);
		}
		addClass.setGrade(grade);
		classRepository.save(addClass);
		logger.info("class added");
			return new ResponseEntity<ClassEntity>(addClass,HttpStatus.OK);
		
		
	}
	@RequestMapping (value="/addStudent/class/{classId}",method=RequestMethod.PUT)
	private ResponseEntity<?> addStudent(@PathVariable Integer classId,@RequestParam Integer studentId)
	{
		StudentEntity student = new StudentEntity();
		ClassEntity updatedClass=new ClassEntity();
		student=studentRepository.findById(studentId).orElse(null);
		updatedClass=classRepository.findById(classId).orElse(null);
		if(student==null)
		{
			return new ResponseEntity<>(new RESTError("Student doesn't exists"),HttpStatus.BAD_REQUEST);
		}if(updatedClass==null)
		{
			return new ResponseEntity<>(new RESTError("Class doesn't exists"),HttpStatus.BAD_REQUEST);
		}
		updatedClass.getStudentClass().add(student);
		classRepository.save(updatedClass);
		return new ResponseEntity<ClassEntity>(updatedClass,HttpStatus.OK);
		
	}
	

}
