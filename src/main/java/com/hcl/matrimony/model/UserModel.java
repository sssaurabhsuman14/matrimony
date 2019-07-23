package com.hcl.matrimony.model;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	
	String email;
	String password;
	String userName;
	LocalDate dateOfBirth;
	String height;
	String maritalStatus;
	String motherTounge;
	String religion;
	String city;
	String mobileNo;
	
}
