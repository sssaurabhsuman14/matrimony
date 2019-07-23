package com.hcl.matrimony.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyLikeModel implements Serializable{
	
	private static final long serialVersionUID = 3579142930232637532L;

	private Long likeId;
	
	private Long followerUserId;
	
	private Long followingUserId;

}
