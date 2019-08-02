package com.iktpreobuka.zavrsniProjekat.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.zavrsniProjekat.entities.UserEntity;;

public interface UserRepository extends CrudRepository <UserEntity,Integer>{

}
