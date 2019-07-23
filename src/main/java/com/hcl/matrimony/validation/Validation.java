package com.hcl.matrimony.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.hcl.matrimony.exception.ApplicationException;


@Component
public class Validation 
{
	 
	public static void validateLoginRequest(String email,String password) throws ApplicationException 
	{
		
		if(!StringUtils.hasText(email)&&!StringUtils.hasText(password))
			throw new ApplicationException("Please check email and password");
		
		
	}

	
}
