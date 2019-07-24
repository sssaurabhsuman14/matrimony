package com.hcl.matrimony.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

		if (followerUser != null && followingUser != null && !isAlreadyLiked) {

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
			throw new ApplicationException(
					"User does not exist or you have already liked the selected user. Please try later !!!");
		}

		return model;

	}

	public List<Long> findFollowerIdByFollowingUserId(Long followingUserId) {
		List<Long> myLikeIdModels = new ArrayList<>();

		List<MyLike> myLikes = myLikeRepository.findByFollowingUserId(followingUserId);

		if (!myLikes.isEmpty()) {
			for (MyLike myLike : myLikes) {
				Long followerId = myLike.getFollowerUserId();
				myLikeIdModels.add(followerId);
			}
		}
		return myLikeIdModels;
	}

	public List<User> fetchFollowList(Long userId) throws ApplicationException {
		User loginUser = userService.getUser(userId);

		List<User> followUserList = new ArrayList<>();
		Optional<List<MyLike>> findByFollowerUserIdOptional = myLikeRepository.findByFollowerUserId(userId);
		boolean isOptionalPresent = findByFollowerUserIdOptional.isPresent();
		if (isOptionalPresent) {
			List<MyLike> myLikeList = findByFollowerUserIdOptional.get();

			for (MyLike myLikeRecord : myLikeList) {
				User user = userService.getUser(myLikeRecord.getFollowingUserId());
				followUserList.add(user);
			}
		} else {
			throw new ApplicationException("Hi, " + loginUser.getUserName() + " You have empty follow List");
		}
		return followUserList;

	}

}
