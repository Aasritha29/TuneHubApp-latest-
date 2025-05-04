package com.example.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tunehub.entities.Users;
import com.example.tunehub.services.UsersService;

@Controller
public class UsersController {

	@Autowired
	 UsersService userv;
	
 
	@PostMapping("/register")
	//model attribute is ussed to pass the data from controller to vie(html/..i.e., frontend)
	public String addUser(@ModelAttribute Users user)
	{
	   boolean userstat=userv.emailExists(user.getEmail());
	   if(userstat==true)
	   {
		   return "registerfail";
	   }
	   
	   else {
		   userv.addUser(user);
		   return "registersuccess";
	   }

	}
	
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password)
	{
		boolean loginstatus=userv.validateUser(email, password);
		 if(loginstatus==true)
		{
			String role=userv.getRole(email);
			
			if(role.equals("admin"))
			return "adminhome";
			
			else
				return "customerhome";
		}
	
		else
		{
			return "loginfail";
		}
		
	}
}