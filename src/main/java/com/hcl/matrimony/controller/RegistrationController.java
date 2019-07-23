
package com.hcl.matrimony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.service.RegistrationService;

@RestController
@RequestMapping("/register")
public class RegistrationController 
{
	@Autowired
	RegistrationService registrationService;

	
	@PostMapping("/reg")
	public ResponseEntity<?> createUser(@RequestBody UserModel user) throws Exception
	{
		registrationService.addUser(user);
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
		
		
		
	}
	
	
}