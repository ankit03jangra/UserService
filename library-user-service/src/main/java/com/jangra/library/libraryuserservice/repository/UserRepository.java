package com.jangra.library.libraryuserservice.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jangra.library.libraryuserservice.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmailId(String email);
	public Long deleteByEmailId(String email);
	
}
