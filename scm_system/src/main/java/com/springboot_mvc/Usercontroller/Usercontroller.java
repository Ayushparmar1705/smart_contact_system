package com.springboot_mvc.Usercontroller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot_mvc.Contact.Contact;
import com.springboot_mvc.Savecontact.Savecontact;
import com.springboot_mvc.Userclass.Userclass;
import com.springboot_mvc.Userdto.Userdto;
import com.springboot_mvc.userrepositry.userrepositry;
import com.springboot_mvc.userservice.Userservice;

@Controller
public class Usercontroller {
	@Autowired
	private UserDetailsService userdetailsservice;
	@Autowired
	userrepositry userrep;
	private Userservice userservice;
	@Autowired
	private Savecontact contactrep;

	public Usercontroller(Userservice userservice) {
		super();
		this.userservice = userservice;
	}
	
	
	
	@GetMapping("/home")
	private String home(Model m, Principal principal)
	{
		UserDetails userdetails = userdetailsservice.loadUserByUsername(principal.getName());
	
		m.addAttribute("userdetail", userdetails);
		return "home";
	}

	@GetMapping("user/contact")
	private String homepage(Model m, Principal principal) {
		UserDetails userdetails = userdetailsservice.loadUserByUsername(principal.getName());
		m.addAttribute("contact", new Contact());
		m.addAttribute("userdetail", userdetails);
		return "Addcontact";
	}

	@GetMapping("/login")
	private String loginpage(Model m, Userdto userdto,Principal principal) {
		m.addAttribute("user", userdto);

		System.out.println(userdto);
		
		return "login-page";
	}

	@GetMapping("/register")
	private String registerpage(Model m) {
		Userdto userdto = new Userdto();

		m.addAttribute("signup", userdto);
		return "signup-page";

	}

	@PostMapping("/register")
	private String addregisterpage(@ModelAttribute("signup") Userdto userdto) {
		userservice.save(userdto);
		System.out.println(userdto);
		System.out.println("Signup succesfully...");
		return "redirect:/register?success";
	}

	@PostMapping("user/addcontact")
	private String addcontact(@ModelAttribute("contact") Contact contact,Principal p) {
		
		String name = p.getName();
		System.out.println("Name : "+name);
		Userclass user = userrep.findByusername(name);
		contact.setUser(user);
	
	
		contactrep.save(contact);
	

		
		return "redirect:/user/contact";
	}
	
	
	@GetMapping("/user/showcontact")
	private String showcontact(Model m,Principal p)
	{
		
	
		String name = p.getName();
		UserDetails userdetails = userdetailsservice.loadUserByUsername(p.getName());
		m.addAttribute("userdetail", userdetails);
		Userclass user = userrep.findByusername(name); 
	
		if(user == null){
			m.addAttribute("error","User not found...");
		}
		else
		{
			List<Contact> contacts = contactrep.findByUserId(user.getId());
			m.addAttribute("list",contacts);
			
			
		}
		return "showcontact";
	}
	@GetMapping("/delete/{id}")
	private String deletecontact(@PathVariable("id")int id)
	{
		
	
		System.out.println(id);
		contactrep.deleteById(id);
		
		return "redirect:/user/showcontact";
	}
	@GetMapping("/update/{id}")
	private String update(Model m , @PathVariable("id")int id , Principal principal)
	{
		
		UserDetails userdetails = userdetailsservice.loadUserByUsername(principal.getName());
	
		m.addAttribute("userdetail", userdetails);
		
	
		Contact contact = contactrep.findById(id).get();
		m.addAttribute("contact",contact);
		return "updatecontact";
	}
	@PostMapping("/updatecontact/{id}")
	private String updatecontact(Model m,@PathVariable("id")int id,@ModelAttribute("contact")Contact contact,Principal principal)
	{
		
		Userclass user = userrep.findByusername(principal.getName());
		contact.setUser(user);
		contact.setId(id);
		contactrep.save(contact);
		return "redirect:/user/showcontact";
	}
	@GetMapping("/user/profile")
	private String userprofile(Model m,Principal p)
	{
		UserDetails userdetails = userdetailsservice.loadUserByUsername(p.getName());
		
		m.addAttribute("userdetail", userdetails);
		String name = p.getName();
		Userclass user = userrep.findByusername(name);
		System.out.println(user);
		m.addAttribute("profile",user);
		return "profile";
	}
	@GetMapping("/user/contactus")
	private String contactus(Principal p,Model m)
	{
		String name = p.getName();
		UserDetails userdetails = userdetailsservice.loadUserByUsername(p.getName());
		m.addAttribute("userdetail", userdetails);

		return "contactus";
	}
	@PostMapping("/user/create_order")
	@ResponseBody
	private String create_order(@RequestBody Map<String, Object> data)
	{
		System.out.println(data);
		return "done";
	}
}
