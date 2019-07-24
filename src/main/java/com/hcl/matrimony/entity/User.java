package com.hcl.matrimony.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {

	public User(User user) {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable=false)
	private Long userId;

	@Email
	@Column(name = "email", nullable=false)
	private String email;

	@Column(name = "password", nullable=false)
	private String password;

	@Column(name = "user_name", nullable=false)
	private String userName;

	@Column(name = "date_of_birth", nullable=false)
	private LocalDate dateOfBirth;

	@Column(name = "height", nullable=false)
	private String height;

	@Column(name = "marital_status", nullable=false)
	private String maritalStatus;

	@Column(name = "mother_tounge", nullable=false)
	private String motherTounge;

	@Column(name = "religion", nullable=false)
	private String religion;

	@Column(name = "city", nullable=false)
	private String city;

	@Column(name = "mobile_No", nullable=false)
	private Long mobileNo;

}
