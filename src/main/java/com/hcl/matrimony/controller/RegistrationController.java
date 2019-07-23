package com.hcl.matrimony.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.UserModel;

@RestController
@RequestMapping("/register")
public class RegistrationController 
{

	
	@PostMapping
	public ResponseEntity<ResponseData> createUser(@RequestBody UserModel user)
	{
		return null;
		
		
		
		
		
	}
	
	
}
