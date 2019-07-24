package com.hcl.matrimony.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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
import com.hcl.matrimony.model.UserModel;
import com.hcl.matrimony.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
	@InjectMocks
	LoginController loginController;

	@Mock
	UserService userServiceMock;

	User user = new User();
	UserModel userModel = new UserModel();

	@Before
	public void setUp() {
		userModel.setEmail("sagargaikwad@gmail.com");
		userModel.setPassword("sagar123");
	}

	@Test
	public void testLoginSuccess() throws ApplicationException {
		Mockito.when(userServiceMock.doLogin(Mockito.anyString(), Mockito.anyString())).thenReturn(userModel);
		ResponseEntity<ResponseData> loginResponse = loginController.login("sagargaikwad966@gmail.com", "sagar123");

		assertNotNull(loginResponse);
		assertEquals("sagargaikwad@gmail.com", loginResponse.getBody().getResponseData());
		assertEquals(200, loginResponse.getBody().getResponseStatus().value());
	}

	@Test(expected = ApplicationException.class)
	public void testLoginFailure() throws ApplicationException {
		Mockito.when(userServiceMock.doLogin(Mockito.anyString(), Mockito.anyString()))
				.thenThrow(ApplicationException.class);
		ResponseEntity<ResponseData> loginResponse = loginController.login("abc@gmail.com", "abc123");
		assertNotNull(loginResponse);
		assertEquals("sagargaikwad@gmail.com", loginResponse.getBody().getResponseData());
		assertEquals(400, loginResponse.getBody().getResponseStatus().value());
	}

	@Test(expected = ApplicationException.class)
	public void testLoginMandatoryFieldEmail() throws ApplicationException {
		ResponseEntity<ResponseData> loginResponse = loginController.login(" ", "abc123");
		assertNotNull(loginResponse);
		assertEquals(400, loginResponse.getBody().getResponseStatus().value());
	}

	@Test(expected = ApplicationException.class)
	public void testLoginMandatoryFieldPassword() throws ApplicationException {
		ResponseEntity<ResponseData> loginResponse = loginController.login("abc@gamil.com", " ");
		assertNotNull(loginResponse);
		assertEquals(400, loginResponse.getBody().getResponseStatus().value());
	}

}
