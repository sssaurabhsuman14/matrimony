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
public class SearchModel {

	private Integer minAge;
	private Integer maxAge;
	private String minHeight;
	private String maxHeight;
	private String religion;
	private String city;
	private String maritalStatus;
	private Long userId;



}
