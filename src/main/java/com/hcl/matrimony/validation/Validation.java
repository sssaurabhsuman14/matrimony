package com.hcl.matrimony.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.hcl.matrimony.entity.MyLike;
import com.hcl.matrimony.exception.ApplicationException;
import com.hcl.matrimony.model.SearchModel;
import com.hcl.matrimony.model.UserModel;

@Component
public class Validation {

	public static void validateLoginRequest(String email, String password) throws ApplicationException {
		if (!StringUtils.hasText(password) || !StringUtils.hasText(email))
			throw new ApplicationException("Please enter mandatory fields");

	}

	public static boolean hasMyLikeEntityAlreadyPresent(MyLike mylike) {
		if(mylike != null) {
			return true;
		}
		return false;
	}

	public static void validateUser(UserModel user) throws ApplicationException {
		if (!StringUtils.hasText(user.getUserName()) || !StringUtils.hasText(user.getReligion()) || !StringUtils.hasText(user.getPassword())
				|| !StringUtils.hasText(user.getMotherTounge()) || user.getMobileNo()==null || !StringUtils.hasText(user.getMaritalStatus())
				|| !StringUtils.hasText(user.getHeight()) || !StringUtils.hasText(user.getEmail()) || user.getDateOfBirth()==null
				|| !StringUtils.hasText(user.getCity()) || user.getAge()==null) {
			throw new ApplicationException("Please fill all the details");

		}
	}

	public static void validateEmail(UserModel user) throws ApplicationException {

		String regex = "^(.+)@(.+)$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user.getEmail());
		if(!matcher.matches()) 
			throw new ApplicationException("Please entre vaild email id");
	}

	public boolean validateSearch(SearchModel searchModel) {

		if(!(ObjectUtils.isEmpty(searchModel.getMinAge()) && ObjectUtils.isEmpty(searchModel.getMaxAge())) || !(ObjectUtils.isEmpty(searchModel.getMinHeight()) && ObjectUtils.isEmpty(searchModel.getMaxHeight())) || !ObjectUtils.isEmpty(searchModel.getReligion()) || !ObjectUtils.isEmpty(searchModel.getMaritalStatus()) || !ObjectUtils.isEmpty(searchModel.getCity())) {
			return true;
		} else {
			return false;
		}

	}
}
