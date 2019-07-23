package com.hcl.matrimony.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.exception.RecordNotFoundException;
import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.SearchModel;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest 
{

	
	@InjectMocks
	SearchController searchcontroller;
	
	@Mock
	UserService userServiceMock;

	SearchModel searchModel = new SearchModel();
	UserModel userModel;
	
	List<User> modellist = new ArrayList<>();
	
	@Before
	public void setUp()
	{

		searchModel.setAge(25);
		searchModel.setHeight("5.7");
		searchModel.setReligion("Hindu");
		searchModel.setCity("pune");
		searchModel.setMaritalStatus("single");
		
		userModel = new UserModel();
		
		User user1=new User(1l,"abc@gmail.com","abc", "abc", LocalDate.now(), "5.7", "single", "marathi", "hindu", "pune", 9552524243l, 25);
		
		//BeanUtils.copyProperties(user1, userModel);
	
		
		modellist.add(user1);
		
		System.out.println(modellist);
	}
	

	@Test(expected = RecordNotFoundException.class)
	public void testsearchProfileWithFailure() throws RecordNotFoundException
	{
		//Mockito.when(userServiceMock.searchProfile(searchModel)).thenReturn(modellist);
		ResponseEntity<ResponseData> searchProfile = (ResponseEntity<ResponseData>) searchcontroller.searchProfile(25,"5.7","Hindu", "Pune", "single");
		assertEquals(400, searchProfile.getBody().getResponseStatus().value());
		
	}
	
	
	
	@Test
	public void testsearchProfileWithsucess() throws RecordNotFoundException
	{
		
		
		Mockito.when(userServiceMock.searchProfile(searchModel)).thenReturn(modellist);
		ResponseEntity<ResponseData> searchProfile = (ResponseEntity<ResponseData>) searchcontroller.searchProfile(25,"5.7","Hindu", "Pune", "single");
		assertEquals(200, searchProfile.getBody().getResponseStatus().value());
		
	}
	
	
	
}
