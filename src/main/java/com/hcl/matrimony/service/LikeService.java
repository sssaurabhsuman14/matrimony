package com.hcl.matrimony.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.matrimony.entity.MyLike;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.repository.LikeRepository;

@Service
public class LikeService {
	
	@Autowired
	LikeRepository myLikeRepository;
	
	@Autowired
	UserService userService;
	
	
	
	public List<UserModel> addLike(Long followerUserId, Long followingUserId){
		
		List<UserModel> model = new ArrayList<>();
		MyLike entity = new MyLike();
		
		entity.setFollowerUserId(followerUserId);
		entity.setFollowingUserId(followingUserId);
		MyLike mylike  = myLikeRepository.save(entity);
		
		if(mylike != null) {
			
		}
		
		
		return model;
		
		
		
	}

}
