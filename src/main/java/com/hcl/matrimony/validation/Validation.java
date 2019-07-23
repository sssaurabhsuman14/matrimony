package com.hcl.matrimony.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.hcl.matrimony.exception.ApplicationException;


@Component
public class Validation 
{
	 
	public static void validateLoginRequest(String email,String password) throws ApplicationException 
	{		
		if(!StringUtils.hasText(password) || !StringUtils.hasText(email))
			throw new ApplicationException("Please enter mandatory fields");

	}

	
}
