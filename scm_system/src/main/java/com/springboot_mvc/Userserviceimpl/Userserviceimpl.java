package com.springboot_mvc.Userserviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot_mvc.Userclass.Userclass;
import com.springboot_mvc.Userdto.Userdto;
import com.springboot_mvc.userrepositry.userrepositry;
import com.springboot_mvc.userservice.Userservice;
@Service
public class Userserviceimpl implements Userservice {
	private userrepositry userrep;
	@Autowired
	PasswordEncoder passwordencoder;
	
	public Userserviceimpl(userrepositry userrep) {
		super();
		this.userrep = userrep;
	}

	@Override
	public Userclass findByusername(String username) {
		
		return userrep.findByusername(username);
	}

	@Override
	public Userclass save(Userdto userdto) {
		Userclass user = new Userclass(userdto.getUsername(),passwordencoder.encode(userdto.getPassword()),userdto.getFullname());
		return userrep.save(user);
	}

}
