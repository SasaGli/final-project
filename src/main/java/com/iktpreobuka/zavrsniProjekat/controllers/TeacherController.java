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
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable Integer id) {

		if (!teacherRepository.findById(id).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("Teacher with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}

		TeacherEntity teacher = teacherRepository.findById(id).get();
		teacherRepository.deleteById(id);
		return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST)
	
	public ResponseEntity<?> addNewTeacher(@RequestBody TeacherEntity newTeacher) {

		if (newTeacher.getName() == null || newTeacher.getName().equals(" ")
				|| newTeacher.getName().equals("")) {
			return new ResponseEntity<RESTError> (new RESTError("Invalid teacher name."), HttpStatus.BAD_REQUEST);
		}
		if (newTeacher.getSurname() == null || newTeacher.getSurname().equals(" ")
						|| newTeacher.getSurname().equals("")) {
			return new ResponseEntity<RESTError> (new RESTError("Invalid teacher surname."), HttpStatus.BAD_REQUEST);
		}
		if ( newTeacher.getPhoneNumber() == null || newTeacher.getPhoneNumber().equals(" ")
						|| newTeacher.getPhoneNumber().equals("")) {
	return new ResponseEntity<RESTError> (new RESTError("Invalid teacher phone number."), HttpStatus.BAD_REQUEST);
}
	
	if (newTeacher.getEmail() == null || newTeacher.getEmail().equals(" ")
							|| newTeacher.getEmail().equals("")) {
return new ResponseEntity<RESTError> (new RESTError("Invalid teacher Email."), HttpStatus.BAD_REQUEST);
}
	if (newTeacher.getJmbg() == null || newTeacher.getJmbg().equals(" ")
					|| newTeacher.getJmbg().equals("")) {
return new ResponseEntity<RESTError> (new RESTError("Invalid teacher Jmbg."), HttpStatus.BAD_REQUEST);
}
		
		return new ResponseEntity<TeacherEntity> (teacherRepository.save(newTeacher), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateTeacher(@PathVariable Integer id, @RequestBody TeacherEntity teacherEntity) {
		TeacherEntity teacher = teacherRepository.findById(id).get();

		if (teacher == null || teacherEntity == null) {
			return new ResponseEntity<RESTError> (new RESTError("Teacher with provided ID not found."), HttpStatus.NOT_FOUND);
		}

		if (teacherEntity.getName() != null || !teacherEntity.getName().equals(" ")
				|| !teacherEntity.getName().equals("")) {
			teacher.setName(teacherEntity.getName());
		}

		if (teacherEntity.getSurname() != null || !teacherEntity.getSurname().equals(" ")
				|| !teacherEntity.getSurname().equals("")) {
			teacher.setSurname(teacherEntity.getSurname());
		}
		if (teacherEntity.getJmbg() != null || !teacherEntity.getJmbg().equals(" ")
				|| !teacherEntity.getJmbg().equals("")) {
			teacher.setJmbg(teacherEntity.getJmbg());
		}
		if (teacherEntity.getEmail() != null || !teacherEntity.getEmail().equals(" ")
				|| !teacherEntity.getEmail().equals("")) {
			teacher.setEmail(teacherEntity.getEmail());
		}
		if (teacherEntity.getPhoneNumber() != null || !teacherEntity.getPhoneNumber().equals(" ")
				|| !teacherEntity.getPhoneNumber().equals("")) {
			teacher.setPhoneNumber(teacherEntity.getPhoneNumber());
		}

		return new ResponseEntity<TeacherEntity> (teacherRepository.save(teacher), HttpStatus.OK);

	}
}
