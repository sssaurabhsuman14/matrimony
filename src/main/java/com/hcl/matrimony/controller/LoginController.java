package com.hcl.matrimony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController 
{
	@Autowired
	UserService userService;
	
	public ResponseEntity<ResponseData> login(@RequestParam("email") String email, @RequestParam("password") String password)
	{
		userService.doLogin(email, password);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
