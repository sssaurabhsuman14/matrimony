package com.hcl.matrimony.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.SearchModel;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LikeService likeService;

	public UserModel doLogin(String email, String password) throws ApplicationException 
	{
		UserModel userModel = new UserModel();
		Optional<User> findByEmailOptional = userRepository.findByEmail(email);

		boolean isOptionalPresent = findByEmailOptional.isPresent();

		if(isOptionalPresent)
		{
			User user = findByEmailOptional.get();
			if(user.getPassword().equals(password))
			{
				BeanUtils.copyProperties(user, userModel);
				return userModel;
			}

			else
				throw new ApplicationException("Hi, "+user.getUserName()+" Password is Incorrect, Please Enter correct credential");
		}
		else
		{
			throw new ApplicationException("User with "+email+" Not found, Please do Registration");
		}
	}


	public List<User> searchProfile(SearchModel searchModel) {

		List<User> userList = userRepository.searchProfiles(searchModel.getAge(), searchModel.getHeight(), searchModel.getReligion(), searchModel.getCity(), searchModel.getMaritalStatus());

		for (User user : userList) {
			if(user.getUserId().equals(searchModel.getUserId())) {
				userList.remove(user);
			}
		}
		return userList;
	}

	public User getUser(Long id) throws ApplicationException {
		User user = new User();
		user = userRepository.findById(id).get();
		if( user != null) {
			return user;
		}else {
			throw new ApplicationException("User with given id does not exists !!!");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
public List<UserModel> getFollowing(Long followingUserId){
		
		List<UserModel> model = new ArrayList<>();
		List<Long> followersUserIdList = likeService.findFollowerIdByFollowingUserId(followingUserId);
		
		List<User> user = userRepository.findByUserIdIn(followersUserIdList);
		
		if( !user.isEmpty() ) {
			for(User use : user ) {
				UserModel userModel = new UserModel();
				BeanUtils.copyProperties(use, userModel);
				model.add(userModel);
			}
		}
		return model;
		
	}

}
