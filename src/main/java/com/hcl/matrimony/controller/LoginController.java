package com.hcl.matrimony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.service.UserService;
import com.hcl.matrimony.validation.Validation;

@RestController
@RequestMapping("/login")
public class LoginController 
{
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<ResponseData> login(@RequestParam("email") String email, @RequestParam("password") String password) throws ApplicationException
	{
		Validation.validateLoginRequest(email, password);
		User loginUser = userService.doLogin(email, password);
		ResponseData response = new ResponseData("Hi, "+loginUser.getUserName()+" you have login Successfully", HttpStatus.OK, loginUser.getUserId());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
