package com.hcl.matrimony.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hcl.matrimony.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public List<User> findByReligion();
	
	public List<User> findByHeight();
	
	public List<User> findByCity();
	
	public Optional<User> findByEmail(String email);

	public List<User> findByMaritalStatus();
}