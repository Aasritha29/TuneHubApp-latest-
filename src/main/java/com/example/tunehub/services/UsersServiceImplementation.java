package com.example.tunehub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tunehub.entities.Users;
import com.example.tunehub.repositories.UsersRepository;

@Service
public class UsersServiceImplementation implements UsersService {

	@Autowired
	UsersRepository userrepo;
	
	@Override
	public String addUser(Users user) {
		userrepo.save(user);
		return "user is created and saved";
	}

	@Override
	public boolean emailExists(String email) {
		if(userrepo.findByEmail(email)==null)
		{
		return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		
		 String email_db=userrepo.findByEmail(email).getEmail();
		
		 String password_db=userrepo.findByEmail(email).getPassword();
		if(password_db.equals(password) && email_db.equals(email))
		{
			return  true;
		}
		 
		return false;
	}

	@Override
	public String getRole(String email) {
		 return (userrepo.findByEmail(email).getRole());
		
	}

	@Override
	public String getEmail(String email) {
		 return (userrepo.findByEmail(email).getEmail());
	}

	@Override
	public Users getUser(String email) {
		// TODO Auto-generated method stub
		return userrepo.findByEmail(email);
	}
}
