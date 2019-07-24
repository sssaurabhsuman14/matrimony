package com.hcl.matrimony.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.service.LikeService;
import com.hcl.matrimony.service.UserService;

@RestController
@RequestMapping("/like")
public class LikeController {

	@Autowired
	LikeService likeService;
	
	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<ResponseData> like(@RequestParam(name = "followerUserId") Long followerUserId,
			@RequestParam(name = "followingUserId") Long followingUserId) throws ApplicationException 
	{
		
		Map<String, UserModel> addLike = likeService.addLike(followerUserId, followingUserId);
		UserModel likedUser = addLike.get("followingUser");
		UserModel  user = addLike.get("followerUser");
		ResponseData response = new ResponseData("Hi, "+user.getUserName()+" you like "+likedUser.getUserName(), HttpStatus.OK, likedUser.getUserName());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
