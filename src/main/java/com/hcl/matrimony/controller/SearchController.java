package com.hcl.matrimony.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.SearchModel;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.service.UserService;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<?> searchProfile(@Valid @RequestBody SearchModel searchModel){
		List<UserModel> userModelList = userService.searchProfile(searchModel);
		
		ResponseData response = new ResponseData("Please find profiles based on search parameters " , HttpStatus.OK, userModelList);

		return new ResponseEntity (response ,HttpStatus.OK);
	}

}
