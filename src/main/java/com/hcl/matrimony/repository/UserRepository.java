package com.hcl.matrimony.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.matrimony.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	public List<User> findByReligion(String religion);

	public List<User> findByHeight(Integer height);

	public List<User> findByCity(String city);

	public List<User> findByMaritalStatus(String maritalStatus);

	public Optional<User> findByEmail(String email);

	public List<User> findByUserIdIn(List<Long> ids);

}