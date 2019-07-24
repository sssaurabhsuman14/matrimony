package com.hcl.matrimony.service;

import java.time.LocalDate;
import java.util.ArrayList;
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

		searchModel = calculateMinAndMaxBirthYear(searchModel);
		List<User> userList = userRepository.searchProfilesCustom(searchModel);

		for (User user : userList) {
			if(user.getUserId().equals(searchModel.getUserId())) {
				userList.remove(new User(user));
			}
		}
		return userList;
	}

	private SearchModel calculateMinAndMaxBirthYear(SearchModel searchModel) {
		int minYear = LocalDate.now().getYear()-searchModel.getMaxAge();
		int maxYear = LocalDate.now().getYear()-searchModel.getMinAge();
		
		searchModel.setMinAge(minYear);
		searchModel.setMaxAge(maxYear);

		return searchModel;
	}


	public User getUser(Long id) throws ApplicationException {
		Optional<User> findByIdOptional = userRepository.findById(id);
		if(findByIdOptional.isPresent()) {
			return findByIdOptional.get();
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


	public List<UserModel> fetchFollowList(Long userId) throws ApplicationException 
	{
		List<UserModel> userModelList = new ArrayList<>();
		List<User> fetchFollowList = likeService.fetchFollowList(userId);
		return mappingEntityListToModelList(fetchFollowList,userModelList);

	}

	private List<UserModel> mappingEntityListToModelList(List<User> fetchFollowList, List<UserModel> userModelList) 
	{

		for(User user : fetchFollowList)
		{
			UserModel userModel = new UserModel();
			BeanUtils.copyProperties(user, userModel);
			userModelList.add(userModel);
		}
		return userModelList;

	}

}
