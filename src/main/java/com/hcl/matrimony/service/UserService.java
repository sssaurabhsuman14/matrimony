package com.hcl.matrimony.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	UserRepository userRepository;

	public User doLogin(String email, String password) throws ApplicationException 
	{
		Optional<User> findByEmailOptional = userRepository.findByEmail(email);
		
		if(findByEmailOptional.isPresent())
		{
			User user = findByEmailOptional.get();
			if(user.getPassword().equals(password))
				return user;
			else
				throw new ApplicationException("Hi, "+user.getUserName()+" Password is Incorrect, Please Enter correct credential");
		}
		else
		{
			throw new ApplicationException("User with "+email+" Not found, Please do Registration");
		}
	}

}
