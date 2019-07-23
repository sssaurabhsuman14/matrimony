
package com.hcl.matrimony.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.repository.UserRepository;

@Service
public class RegistrationService {
	
	
	@Autowired
	UserRepository userRepository;
	

	public String addUser(UserModel userdto) throws Exception{
	
		User user= new User();
		BeanUtils.copyProperties(userdto, user);
		
		System.out.println(user);
		
		userRepository.save(user);
		return "Success";
		
	}

}

