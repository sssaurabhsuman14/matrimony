package com.hcl.matrimony.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	

	@GetMapping("/{userId}")
	public ResponseEntity<?> follow(@PathVariable ("userId") Long userId){
		
		return new ResponseEntity<>(userService.getFollowing(userId), HttpStatus.OK);
	}
	
	@GetMapping("/follow/{userId}")
	public ResponseEntity<ResponseData> showFollowList(@PathVariable("userId") Long userId) throws ApplicationException
	{
		if(ObjectUtils.isEmpty(userId))
			throw new ApplicationException("Please Enter the Mandatory Field");
		
		User user = userService.getUser(userId);
		
		List<UserModel> fetchFollowList = (List<UserModel>) userService.fetchFollowList(userId);
		ResponseData response = new ResponseData("Hi, "+user.getUserName()+" Please find your Follow List", HttpStatus.OK, fetchFollowList);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
