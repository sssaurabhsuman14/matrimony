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
import org.springframework.http.ResponseEntity;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.ResponseData;
import com.hcl.matrimony.model.SearchModel;
import com.hcl.matrimony.service.UserService;
import com.hcl.matrimony.validation.Validation;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest 
{


	@InjectMocks
	SearchController searchcontroller;

	@Mock
	UserService userService;
	
	@Mock
	Validation validation;

	SearchModel searchModel;
	User user = new User();
	List<User> userList = new ArrayList<User>();

	@Before
	public void setUp()
	{
		searchModel = new SearchModel();
		searchModel.setMinAge(25);
		searchModel.setMaxAge(27);
		searchModel.setMinHeight("5");
		searchModel.setMaxHeight("7");
		searchModel.setUserId(1L);
		searchModel.setReligion("Hindu");
		searchModel.setCity("pune");
		searchModel.setMaritalStatus("single");

		user.setCity("pune");
		user.setDateOfBirth(LocalDate.of(1992, 11, 06));
		user.setEmail("priyanka@gmail.com");
		user.setHeight("6");
		user.setMaritalStatus("single");
		user.setMobileNo(4466L);
		user.setMotherTounge("hindi");
		user.setPassword("admin");
		user.setReligion("Hindu");
		user.setUserId(1L);
		user.setUserName("Priya");

		userList.add(new User());
		userList.add(new User());
		
		
		

	}

	@Test
	public void testsearchProfileWithSuccess() throws ApplicationException
	{
		Mockito.when(validation.validateSearch(searchModel)).thenReturn(true);
		Mockito.when(userService.searchProfile(searchModel)).thenReturn(userList);
		ResponseEntity<ResponseData> response = searchcontroller.searchProfile(searchModel);
        assertNotNull(response);
        assertEquals(200, response.getBody().getResponseStatus().value());
	}
	
	@Test()
	public void testsearchProfileWithNoParams() throws ApplicationException
	{
		Mockito.when(validation.validateSearch(searchModel)).thenReturn(false);
		Mockito.when(userService.searchProfile(searchModel)).thenReturn(userList);
		ResponseEntity<ResponseData> response = searchcontroller.searchProfile(searchModel);
        assertNotNull(response);
        assertEquals(400, response.getBody().getResponseStatus().value());

	}
}