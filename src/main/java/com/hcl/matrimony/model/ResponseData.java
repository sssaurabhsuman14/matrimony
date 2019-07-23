package com.hcl.matrimony.model;


import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
	
	private String responseMessage;
	private HttpStatus responseStatus;
	private Object responseData;

}
