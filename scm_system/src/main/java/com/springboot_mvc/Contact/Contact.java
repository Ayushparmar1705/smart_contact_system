package com.springboot_mvc.Contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot_mvc.Userclass.Userclass;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String contact_name;
	private Long contact_no;
	private String email;
	private String work;
	@ManyToOne
	@JsonIgnore
	private Userclass user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public Long getContact_no() {
		return contact_no;
	}
	public void setContact_no(Long contact_no) {
		this.contact_no = contact_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	
	
	public Userclass getUser() {
		return user;
	}
	public void setUser(Userclass user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", contact_name=" + contact_name + ", contact_no=" + contact_no + ", email="
				+ email + ", work=" + work + ", user=" + user + "]";
	}
	
	
	
}
