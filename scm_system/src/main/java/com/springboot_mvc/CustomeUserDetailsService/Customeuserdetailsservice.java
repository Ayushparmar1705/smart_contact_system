package com.springboot_mvc.CustomeUserDetailsService;


import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot_mvc.CustomeUserDetails.Customeuserdetails;
import com.springboot_mvc.Userclass.Userclass;
import com.springboot_mvc.userrepositry.userrepositry;
@Service
public class Customeuserdetailsservice implements UserDetailsService{
	@Autowired
	private userrepositry userrep;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userclass user = userrep.findByusername(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("Username and password not found...");
		}
		return new Customeuserdetails(user.getUsername(), user.getPassword(), authorities(), user.getFullname());
	}
	
	public Collection<? extends GrantedAuthority> authorities(){
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

}
