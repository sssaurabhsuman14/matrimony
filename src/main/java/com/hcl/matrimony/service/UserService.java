package com.hcl.matrimony.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

	public User doLogin(String email, String password) throws ApplicationException 
	{
		Optional<User> findByEmailOptional = userRepository.findByEmail(email);

		if(findByEmailOptional.isPresent())
		{
			User user = findByEmailOptional.get();
			if(user.getPassword().equals(password))
				return user;
			else
				throw new ApplicationException("Hi, "+user.getUserName()+" Password is Incorrect, Please Enter correct credential");
		}
		else
		{
			throw new ApplicationException("User with "+email+" Not found, Please do Registration");
		}
	}
	
	public User getUser(Long id) {
		return userRepository.findById(id).get();

	}

	public List<UserModel> searchProfile(SearchModel searchModel) {

		List<User> userList = userRepository.searchProfiles(searchModel.getAge(), searchModel.getHeight(), searchModel.getReligion(), searchModel.getCity(), searchModel.getMaritalStatus());
		List<UserModel> userModelList = new ArrayList<UserModel>();
		
		BeanUtils.copyProperties(userList, userModelList);

		return userModelList;
	}

}
