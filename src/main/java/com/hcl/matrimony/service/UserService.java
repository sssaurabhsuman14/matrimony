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
	

	public List<UserModel> searchProfile(SearchModel searchModel) {

		//int requiredDOBYear = LocalDate.now().getYear()-searchModel.getAge();
		List<User> userList = userRepository.searchProfiles(searchModel.getAge(), searchModel.getHeight(), searchModel.getReligion(), searchModel.getCity(), searchModel.getMaritalStatus());
		List<UserModel> userModelList = new ArrayList<UserModel>();
		
		BeanUtils.copyProperties(userList, userModelList);

		return userModelList;
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

}
