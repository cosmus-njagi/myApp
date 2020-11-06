package com.afrisoln;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restcontroler {
	@Autowired
	private Userservice userservice;
	@GetMapping("/")
public String hello(HttpServletRequest request) {
	return "welcome";
}
	@GetMapping("/save-user")
	public String saveuser(@RequestParam String username,@RequestParam String firstname,@RequestParam String lastname,@RequestParam int age,@RequestParam String password) {
		
		User user=new User(username,firstname,lastname,age,password);
		userservice.savemyuser(user);
		return "User Saved";
	}
}
