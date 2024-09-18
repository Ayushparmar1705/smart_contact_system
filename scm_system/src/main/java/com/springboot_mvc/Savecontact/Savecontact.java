package com.springboot_mvc.Savecontact;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.springboot_mvc.Contact.Contact;

public interface Savecontact extends CrudRepository<Contact, Integer> {
	 List<Contact> findByUserId(int userId);
	 public List<Contact> findByEmail(String keyword);
	
}
