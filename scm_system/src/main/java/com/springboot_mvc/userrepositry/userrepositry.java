package com.springboot_mvc.userrepositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot_mvc.Userclass.Userclass;
@Repository
public interface userrepositry extends CrudRepository<Userclass, Integer> {
	
Userclass findByusername(String username);
}
