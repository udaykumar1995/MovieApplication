package com.company.movie.entity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.movie.entity.User;

public interface UserRepository extends CrudRepository<User, Long>  {

	User findByUserName(String userName);
	List<User> findAll(); 
}
