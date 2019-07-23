package com.hcl.matrimony.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	UserRepository userRepository;

	public void doLogin(String email, String password) 
	{
		Optional<User> findByEmailOptional = userRepository.findByEmail(email);
		
		if(findByEmailOptional.isPresent())
		{
			User user = findByEmailOptional.get();
			if(user.getPassword().equals(password))
				return user;
			else
				throw new ApplicationException("Password is Incorrect, Please Enter correct credential");
		}
		else
		{
			throw new ApplicationException("User Not found, Please do Registration");
		}
		
	}

}
