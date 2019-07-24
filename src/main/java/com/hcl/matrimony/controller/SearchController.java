package com.hcl.matrimony.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.SearchModel;
import com.hcl.matrimony.service.UserService;
import com.hcl.matrimony.validation.Validation;

@Controller
@RequestMapping("/search}")
public class SearchController {

	@Autowired
	UserService userService;

	@Autowired
	Validation validation;

	private static final Logger logger = LogManager.getLogger(SearchController.class);

	@PostMapping
	public ResponseEntity<ResponseData> searchProfile(@Valid @RequestBody SearchModel searchModel) throws ApplicationException{

		logger.info("-------------Inside searchProfileMethod----------------------");
		boolean isValidated = validation.validateSearch(searchModel);
		if(isValidated) {
			List<User> userList = userService.searchProfile(searchModel);
			if (!userList.isEmpty()) {
				ResponseData response = new ResponseData("Please find below profiles based on search parameters " , HttpStatus.OK, userList);
				return  new ResponseEntity<ResponseData> (response ,HttpStatus.OK);
			} 
		}else {

			ResponseData response = new ResponseData("No search criteria is given. Please provide atleast one parameter. ", HttpStatus.BAD_REQUEST, null);

			return  new ResponseEntity<ResponseData> (response ,HttpStatus.OK);
		}

		throw new ApplicationException("No matching profiles found with specified criteria.");
	}

}
