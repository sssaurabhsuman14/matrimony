package com.hcl.matrimony.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.model.UserModel;

@RestController
@RequestMapping("/like")
public class LikeController {
	
	
	@PostMapping 
	public ResponseEntity<?> like(@RequestParam(name = "followerUserId") Long followerUserId,
									@RequestParam(name = "followingUserId") Long followingUserId){
		
		
		return null;
		
	}

}
