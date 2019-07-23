package com.hcl.matrimony.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="my_like")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "likeId")
public class MyLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "like_id", nullable=false)
	private Long likeId;
	
	@Column(name = "follower_user_id", nullable=false)
	private Long followerUserId;
	
	@Column(name = "following_user_id", nullable=false)
	private Long followingUserId;
	
}
