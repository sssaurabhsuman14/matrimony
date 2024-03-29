package com.hcl.matrimony.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.matrimony.entity.MyLike;

@Repository
public interface LikeRepository extends JpaRepository<MyLike, Long> {

	public MyLike findByFollowerUserIdAndFollowingUserId(Long followerUserId,Long followingUserId);
	
	public List<MyLike> findByFollowingUserId(Long followingUserId);
	public Optional<List<MyLike>> findByFollowerUserId(Long userId);
	
}
