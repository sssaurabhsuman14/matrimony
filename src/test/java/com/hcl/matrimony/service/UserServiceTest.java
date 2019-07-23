package com.hcl.matrimony.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.matrimony.controller.LoginController;
import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.SearchModel;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	UserService userService;
	
	@Mock
	UserRepository userRepositoryMock;
	
	@Mock
	LoginController loginController;
		
	User user = new User();
	UserModel userModel = new UserModel();
	Optional<User> optionalUser;
	SearchModel searchModel = new SearchModel();
	List<User> userList = new ArrayList<User>();
	
	@Before
	public void setUp()
	{	
		user.setEmail("sagargaikwad@gmail.com");
		user.setPassword("sagar123");
		user.setUserName("Sagar Gaikwad");
		
		//BeanUtils.copyProperties(userModel, user);
		optionalUser = Optional.of(user);
		
		searchModel.setAge((Integer)21);
		searchModel.setCity("pune");
		searchModel.setHeight("6");
		searchModel.setMaritalStatus("single");
		searchModel.setReligion("hindu");
		searchModel.setUserId(1L);
		
		user.setAge((Integer)21);
		user.setCity("pune");
		user.setDateOfBirth(LocalDate.parse("1992-11-06"));
		user.setEmail("abc@gmail.com");
		user.setHeight("6");
		user.setMaritalStatus("single");
		user.setMobileNo(987654L);
		user.setMotherTounge("hindi");
		user.setPassword("admin");
		user.setReligion("hindu");
		user.setUserId(1L);
		user.setUserName("Priyanka");
		
		userList.add(user);
		
	}

	@Test
	public void TestDoLoginSuccess() throws ApplicationException
	{
		Mockito.when(userRepositoryMock.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
		userModel = userService.doLogin("sagargaikwad966@gmail.com", "sagar123");
		assertNotNull(userModel);
	}
	
	@Test(expected = ApplicationException.class)
	public void TestDoLoginWrongPassword() throws ApplicationException
	{
		Mockito.when(userRepositoryMock.findByEmail(Mockito.anyString())).thenReturn(optionalUser);
		userModel = userService.doLogin("sagargaikwad966@gmail.com", "abc123");
		assertEquals("abc123", userModel.getPassword());	
	}
	
	@Test(expected = ApplicationException.class)
	public void TestDoLoginWrongUser() throws ApplicationException
	{

		Mockito.when(userRepositoryMock.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		UserModel userModel = userService.doLogin("sagargaikwad966@gmail.com", "sagar123");
		assertNotEquals("sagar123", userModel.getPassword());
	}

	@Test
	public void searchProfile() {
		Mockito.when(userRepositoryMock.searchProfiles((Integer)21, "pune", "6", "single", "hindu")).thenReturn(userList);
		List<User> userListService = userService.searchProfile(searchModel);
		assertNotEquals(userList, userListService);
	}
}
