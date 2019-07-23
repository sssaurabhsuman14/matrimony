package com.hcl.matrimony.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hcl.matrimony.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public List<User> findByReligion(String religion);
	
	public List<User> findByHeight(Integer height);
	
	public List<User> findByCity(String city);

	public List<User> findByMaritalStatus(String maritalStatus);

	public Optional<User> findByEmail(String email);

	public List<User> findByMaritalStatus();
	
	@Query(value="select * from user where age = (:age) and height = (:height) and religion= (:religion)"
			+ " and city = (:city) and marital_status = (:maritalStatus) ", nativeQuery = true)
	List<User> searchProfiles (int age, String height, String religion, String city, String maritalStatus);

}