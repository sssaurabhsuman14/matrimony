package com.hcl.matrimony.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.hcl.matrimony.controller.LoginController;
import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest 
{
	@InjectMocks
	UserService userService;
	
	@Mock
	UserRepository userRepositoryMock;
	
	@Mock
	LoginController loginController;
	
	User user = new User();
	UserModel userModel = new UserModel();
	Optional<User> optionalUser;
	
	@Before
	public void setUp()
	{	
		userModel.setEmail("sagargaikwad@gmail.com");
		userModel.setPassword("sagar123");
		userModel.setUserName("Sagar Gaikwad");
		
		BeanUtils.copyProperties(userModel, user);
		optionalUser = Optional.of(user);
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
		assertNotEquals("sagargaikwad966@gmail.com", userModel.getEmail());	
	}
	
	@Test(expected = ApplicationException.class)
	public void TestDoLoginWrongUser() throws ApplicationException
	{

		Mockito.when(userRepositoryMock.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		UserModel userModel = userService.doLogin("sagargaikwad966@gmail.com", "sagar123");
		assertNotEquals("sagar123", userModel.getPassword());
	}
	
}
