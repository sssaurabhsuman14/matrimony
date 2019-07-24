package com.hcl.matrimony.controller;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.service.LikeService;

@RunWith(MockitoJUnitRunner.class)
public class LikeControllerTest {

	@InjectMocks

	LikeController likeController;

	@Mock

	LikeService likeService;

	Map<String, UserModel> model = new HashMap<>();

	@Before

	public void setUp() {

		model = new HashMap<>();

		UserModel followerUser = new UserModel();

		followerUser.setUserName("saurabh");

		followerUser.setAge(24);

		UserModel followingUser = new UserModel();

		followingUser.setUserName("saurabh");

		followingUser.setAge(27);

		model.put("followerUser", followerUser);

		model.put("followingUser", followingUser);

	}

	@Test
	public void likeWithSuccess() throws ApplicationException {

		Mockito.when(likeService.addLike(Mockito.anyLong(), Mockito.anyLong())).thenReturn(model);

		assertNotNull(likeController.like(Mockito.anyLong(), Mockito.anyLong()));

	}

}
