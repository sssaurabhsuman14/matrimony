package com.hcl.matrimony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.SearchModel;
import com.hcl.matrimony.service.UserService;

@Controller
@RequestMapping("/search/{age}/{height}/{religion}/{city}/{maritalStatus}/{userId}")
public class SearchController {

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<ResponseData> searchProfile(@PathVariable(value = "age") Integer age, @PathVariable(value = "height") String height, @PathVariable(value = "religion") String religion, @PathVariable(value = "city") String city, @PathVariable(value = "maritalStatus") String maritalStatus, @PathVariable(value = "userId") Long userId) throws ApplicationException{

		SearchModel searchModel = new SearchModel();
		searchModel.setAge(age);
		searchModel.setHeight(height);
		searchModel.setReligion(religion);
		searchModel.setCity(city);
		searchModel.setMaritalStatus(maritalStatus);
		searchModel.setUserId(userId);
		
		List<User> userList = userService.searchProfile(searchModel);

		if (!userList.isEmpty()) {
			ResponseData response = new ResponseData("Please find below profiles based on search parameters " , HttpStatus.OK, userList);

			return new ResponseEntity<> (response ,HttpStatus.OK);
		}
		throw new ApplicationException("No matching profiles found with specified criteria.");
	}

}
