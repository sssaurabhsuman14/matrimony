package com.hcl.matrimony.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest 
{
	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userServiceMock;
	
	User user = new User();
	List<UserModel> fetchFollowList = new ArrayList<>();
	
	@Before
	public void setUp()
	{
		UserModel userModel1 = new UserModel();
		userModel1.setUserName("User1");
		userModel1.setEmail("user1@gmail.com");
		userModel1.setPassword("user123");
		
		fetchFollowList.add(userModel1);
		
		UserModel userModel2 = new UserModel();
		userModel2.setUserName("User1");
		userModel2.setEmail("user1@gmail.com");
		userModel2.setPassword("user123");
		
		fetchFollowList.add(userModel2);
		
		
	}
	
	
	@Test
	public void testShowFollowList() throws ApplicationException
	{
		Mockito.when(userServiceMock.getUser(Mockito.anyLong())).thenReturn(user);
		Mockito.when(userServiceMock.fetchFollowList(Mockito.anyLong())).thenReturn(fetchFollowList);
		ResponseEntity<ResponseData> followResponse = (ResponseEntity<ResponseData>) userController.showFollowList(1L);
		
		List<UserModel> responseList = (List<UserModel>) followResponse.getBody().getResponseData();
		
		assertNotNull(followResponse);
		assertEquals(200, followResponse.getBody().getResponseStatus().value());
		assertEquals(fetchFollowList, responseList);
	}
	


}
