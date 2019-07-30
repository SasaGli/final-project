package com.iktpreobuka.zavrsniProjekat.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.zavrsniProjekat.controllers.util.RESTError;
import com.iktpreobuka.zavrsniProjekat.entities.AdminEntity;
import com.iktpreobuka.zavrsniProjekat.entities.ParentEntity;
import com.iktpreobuka.zavrsniProjekat.entities.StudentEntity;
import com.iktpreobuka.zavrsniProjekat.entities.TeacherEntity;
import com.iktpreobuka.zavrsniProjekat.entities.UserAccountEntity;
import com.iktpreobuka.zavrsniProjekat.entities.UserEntity;
import com.iktpreobuka.zavrsniProjekat.entities.dto.UserDTO;
import com.iktpreobuka.zavrsniProjekat.repositories.AdminRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.ParentRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.StudentRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.TeacherRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.UserAccountRepository;
import com.iktpreobuka.zavrsniProjekat.repositories.UserRepository;


@RestController
@RequestMapping("/userAccount")
public class UserController {
	@Autowired AdminRepository adminRepository;
	@Autowired StudentRepository studentRepository;
	@Autowired ParentRepository parentRepository;
	@Autowired TeacherRepository teacherRepository;
	@Autowired UserRepository userRepository;
	@Autowired UserAccountRepository userAccountRepository;
	private UserEntity user;
	private UserAccountEntity userAccount;
	private AdminEntity admin;
	private StudentEntity student;
	private ParentEntity parent;
	private TeacherEntity teacher;
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> getAllusers()
	
	{
		return new ResponseEntity<Iterable<UserEntity>>(userRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		if (userRepository.findById(id).isPresent()) {
			return new ResponseEntity<UserEntity>(userRepository.findById(id).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("user with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}

	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteuser(@PathVariable Integer id) {

		if (!userRepository.findById(id).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("user with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}

		UserEntity user = userRepository.findById(id).get();
		userRepository.deleteById(id);
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST)
	
	public ResponseEntity<?> addNewuser(@Valid @RequestBody UserDTO newuser,BindingResult result) {

if(result.hasErrors())
{
	return new ResponseEntity<> (createErrorMessage(result), HttpStatus. BAD_REQUEST );
}
	userAccount.setPassword(newuser.getPassword());
	userAccount.setUsername(newuser.getUsername());
	userAccount.setRole(newuser.getRole());
	userAccountRepository.save(userAccount);
	
	user.setEmail(newuser.getEmail());
	user.setJmbg(newuser.getJmbg());
	user.setName(newuser.getName());
	user.setPhoneNumber(newuser.getPhoneNumber());
	user.setSurname(newuser.getSurname());
	user.getUserAccount().add(userAccount);
	userRepository.save(user);
	
	
	if(newuser.getRole().equals("ROLE_ADMIN"))
	{
		admin.setId(user.getId());
		adminRepository.save(admin);
		
		
	}
	if(newuser.getRole().equals("ROLE_STUDENT"))
	{
		student.setId(user.getId());
		studentRepository.save(student);
		
		
	}
	if(newuser.getRole().equals("ROLE_PARENT"))
	{
		parent.setId(user.getId());
		parentRepository.save(parent);
		
		
	}
	if(newuser.getRole().equals("ROLE_TEACHER"))
	{
		teacher.setId(user.getId());
		teacherRepository.save(teacher);
		
		
	}
		
		return new ResponseEntity<UserEntity> (user, HttpStatus.OK);
		
	}
	private String createErrorMessage (BindingResult result)
	{
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining (" "));
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateuser(@PathVariable Integer id,@Valid @RequestBody UserEntity userEntity,BindingResult result) {
		UserEntity user = userRepository.findById(id).get();

		if(result.hasErrors())
		{
			return new ResponseEntity<> (createErrorMessage(result), HttpStatus. BAD_REQUEST );
		}

		return new ResponseEntity<UserEntity> (userRepository.save(user), HttpStatus.OK);

	}
}

