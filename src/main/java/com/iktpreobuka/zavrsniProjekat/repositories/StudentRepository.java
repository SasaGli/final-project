package com.iktpreobuka.zavrsniProjekat.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.zavrsniProjekat.entities.StudentEntity;

public interface StudentRepository extends CrudRepository <StudentEntity,Integer> {

}
