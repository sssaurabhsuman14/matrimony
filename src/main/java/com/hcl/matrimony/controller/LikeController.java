package com.hcl.matrimony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.service.LikeService;

@RestController
@RequestMapping("/like")
public class LikeController {

	@Autowired
	LikeService likeService;

	@PostMapping
	public ResponseEntity<?> like(@RequestParam(name = "followerUserId") Long followerUserId,
			@RequestParam(name = "followingUserId") Long followingUserId) throws ApplicationException {
		
		return new ResponseEntity<>(likeService.addLike(followerUserId, followingUserId), HttpStatus.OK);
	}

}
