package com.hcl.matrimony.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcl.matrimony.exception.RecordNotFoundException;
import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.SearchModel;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.service.UserService;

@Controller
@RequestMapping("/search/{age}/{height}/{religion}/{city}/{maritalStatus}")
public class SearchController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<?> searchProfile(@PathVariable(value = "age") int age, @PathVariable(value = "height") String height, @PathVariable(value = "religion") String religion, @PathVariable(value = "city") String city, @PathVariable(value = "maritalStatus") String maritalStatus){
		
		SearchModel searchModel = new SearchModel();
		searchModel.setAge(age);
		searchModel.setHeight(height);
		searchModel.setReligion(religion);
		searchModel.setCity(city);
		searchModel.setMaritalStatus(maritalStatus);
		
		List<UserModel> userModelList = userService.searchProfile(searchModel);
		
		if (!userModelList.isEmpty()) {
		ResponseData response = new ResponseData("Please find below profiles based on search parameters " , HttpStatus.OK, userModelList);

		return new ResponseEntity (response ,HttpStatus.OK);
		}
		throw new RecordNotFoundException("No matching profiles found with specified criteria.");
	}

}
