package com.hcl.matrimony.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.matrimony.entity.MyLike;
import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.repository.LikeRepository;
import com.hcl.matrimony.validation.Validation;

@Service
public class LikeService {

	@Autowired
	LikeRepository myLikeRepository;

	@Autowired
	UserService userService;

	public Map<String, UserModel> addLike(Long followerUserId, Long followingUserId) throws ApplicationException {

		Map<String, UserModel> model = new HashMap<>();
		MyLike entity = new MyLike();

		User followerUser = userService.getUser(followerUserId);
		User followingUser = userService.getUser(followingUserId);
		
		MyLike myLike = myLikeRepository.findByFollowerUserIdAndFollowingUserId(followerUserId, followingUserId);
		
		boolean isAlreadyLiked = Validation.hasMyLikeEntityAlreadyPresent(myLike);
		
		if( followerUser != null && followingUser != null && !isAlreadyLiked) {
			
			entity.setFollowerUserId(followerUserId);
			entity.setFollowingUserId(followingUserId);
			myLikeRepository.save(entity);

			UserModel userFollower = new UserModel();
			BeanUtils.copyProperties(followerUser, userFollower);

			UserModel userFollowing = new UserModel();
			BeanUtils.copyProperties(followingUser, userFollowing);

			model.put("followerUser", userFollower);
			model.put("followingUser", userFollowing);
		} else {
			throw new ApplicationException("User does not exist or you have already liked the selected user. Please try later !!!");
		}

		return model;

	}

}
