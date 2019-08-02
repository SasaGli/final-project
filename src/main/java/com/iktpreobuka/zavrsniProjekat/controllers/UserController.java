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
import com.iktpreobuka.zavrsniProjekat.enumerations.ERole;
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
		
		if (newuser.getRole() != ERole.ROLE_ADMIN && newuser.getRole() != ERole.ROLE_PARENT
				&& newuser.getRole() != ERole.ROLE_STUDENT && newuser.getRole() != ERole.ROLE_TEACHER) {
			return new ResponseEntity<RESTError>(new RESTError("User role is invalid."),
					HttpStatus.BAD_REQUEST);
		}
		
if(result.hasErrors())
{
	return new ResponseEntity<> (createErrorMessage(result), HttpStatus. BAD_REQUEST );
}
	
	UserEntity user = new UserEntity();
	user.setEmail(newuser.getEmail());
	user.setJmbg(newuser.getJmbg());
	user.setName(newuser.getName());
	user.setPhoneNumber(newuser.getPhoneNumber());
	user.setSurname(newuser.getSurname());
	
	UserAccountEntity userAccount=new UserAccountEntity();	
	userAccount.setPassword(newuser.getPassword());
	userAccount.setUsername(newuser.getUsername());
	userAccount.setRole(newuser.getRole());
	userAccount.setUser(user);
	
	
	
	userRepository.save(user);
	userAccountRepository.save(userAccount);
	
	if(newuser.getRole().name().equals("ROLE_ADMIN"))
	{
		AdminEntity admin=new AdminEntity();
		admin.setId(user.getId());
		admin.setEmail(newuser.getEmail());
		admin.setJmbg(newuser.getJmbg());
		admin.setName(newuser.getName());
		admin.setPhoneNumber(newuser.getPhoneNumber());
		admin.setSurname(newuser.getSurname());
		adminRepository.save(admin);
		
		
	}
	if(newuser.getRole().name().equals("ROLE_STUDENT"))
	{
		StudentEntity student=new StudentEntity();
		student.setId(user.getId());
		student.setEmail(newuser.getEmail());
		student.setJmbg(newuser.getJmbg());
		student.setName(newuser.getName());
		student.setPhoneNumber(newuser.getPhoneNumber());
		student.setSurname(newuser.getSurname());
		studentRepository.save(student);
		
		
	}
	if(newuser.getRole().name().equals("ROLE_PARENT"))
	{
		ParentEntity parent=new ParentEntity();
		parent.setId(user.getId());
		parent.setEmail(newuser.getEmail());
		parent.setJmbg(newuser.getJmbg());
		parent.setName(newuser.getName());
		parent.setPhoneNumber(newuser.getPhoneNumber());
		parent.setSurname(newuser.getSurname());
		parentRepository.save(parent);
		
		
	}
	if(newuser.getRole().name().equals("ROLE_TEACHER"))
	{
		TeacherEntity teacher =new TeacherEntity();
		teacher.setId(user.getId());
		teacher.setEmail(newuser.getEmail());
		teacher.setJmbg(newuser.getJmbg());
		teacher.setName(newuser.getName());
		teacher.setPhoneNumber(newuser.getPhoneNumber());
		teacher.setSurname(newuser.getSurname());
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

