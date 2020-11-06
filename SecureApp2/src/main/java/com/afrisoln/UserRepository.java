package com.afrisoln;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {		//where User is the intended class

	public User findByUsernameAndPassword(String username,String password);
	
	@Query(value="SELECT u FROM User u WHERE u.username LIKE '%' || :keyword || '%'" 
			+"OR u.firstname LIKE '%' || :keyword || '%'"
			+"OR u.lastname LIKE '%' || :keyword || '%'")
	public List<User>search(@Param("keyword")String keyword);
}
