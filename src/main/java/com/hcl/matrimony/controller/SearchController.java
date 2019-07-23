package com.hcl.matrimony.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcl.matrimony.model.SearchModel;
import com.hcl.matrimony.service.UserService;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	UserService userService;
	
	//@GetMapping
	/*public ResponseEntity<?> searchFlight(@Valid @RequestBody SearchModel searchModel){
		userService
		return new ResponseEntity (user ,HttpStatus.OK);		
	}*/

}
