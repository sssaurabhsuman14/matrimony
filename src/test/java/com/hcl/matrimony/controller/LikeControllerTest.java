package com.hcl.matrimony.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.service.LikeService;

@RunWith(MockitoJUnitRunner.class)
public class LikeControllerTest {
	
	@InjectMocks
	LikeController likeController;
	
	@Mock
	LikeService likeService;
	
	Long followerUserId = null;
	Long followingUserId = null;
	Map<String, UserModel> model = new HashMap<>() ;
	
	@Before
	public void setUp() {
		followerUserId = 1l;
		followingUserId = 2l;
		
		model = new HashMap<>();
		User followerUser = new User();
		
	}
	
	
	
	
	@Test(expected = ApplicationException.class)
	public void likeWithSuccess() {
		
		//Mockito.when(likeService.addLike(followerUserId, followingUserId)).thenReturn(model);
		
		//;
		
		
	}

}
