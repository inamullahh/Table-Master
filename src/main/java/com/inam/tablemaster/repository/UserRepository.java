package com.inam.tablemaster.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inam.tablemaster.models.User;



@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findAll();
	
	Optional<User> findByEmail(String email);
	
}
