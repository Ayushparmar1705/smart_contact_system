package com.springboot_mvc.CustomeUserDetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Customeuserdetails implements UserDetails {
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private String fullname;
	

	
	

	public Customeuserdetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String fullname) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.fullname = fullname;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
	
		return username;
	}
	public boolean isAccountNonExpired() {
		return true;
	}


	public boolean isAccountNonLocked() {
		return true;
	}


	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}
