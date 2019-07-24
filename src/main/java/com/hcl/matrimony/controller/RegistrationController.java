
package com.hcl.matrimony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.service.RegistrationService;
import com.hcl.matrimony.validation.Validation;

@RestController
@RequestMapping("/register")
public class RegistrationController {
	@Autowired
	RegistrationService registrationService;

	@PostMapping("/reg")
	public ResponseEntity<ResponseData> createUser(@RequestBody UserModel user) throws ApplicationException
	{
		Validation.validateUser(user);
		Validation.validateEmail(user);
		registrationService.addUser(user);
		ResponseData response = new ResponseData("Hurrayy!!!, "+user.getUserName()+" Welcome to Matrimony", HttpStatus.OK, user.getEmail());
		return new ResponseEntity<>(response, HttpStatus.OK);		
		 
		
		
		
	}

}