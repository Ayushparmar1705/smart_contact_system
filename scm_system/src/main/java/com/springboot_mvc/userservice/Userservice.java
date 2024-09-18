package com.springboot_mvc.userservice;

import com.springboot_mvc.Userclass.Userclass;
import com.springboot_mvc.Userdto.Userdto;

public interface Userservice  {
	Userclass findByusername(String username);
	Userclass save(Userdto userdto);
}
