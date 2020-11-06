package com.afrisoln;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
@Service
@Transactional	//since is communicating with the database
public class Userservice {
	private final UserRepository userrepository;
		public Userservice(UserRepository userrepository) {
		this.userrepository=userrepository;
	}
	public void savemyuser(User user) {
		
		userrepository.save(user);
	}
		public List<User>showallusers(){
		List<User>users=new ArrayList<User>();
		for(User user:userrepository.findAll()) {
			users.add(user);
	}
		return users;
		
	}
	
	public void deletemyuser(int id) {
		userrepository.deleteById(id);
	}
	
	public User get(int id) {
	Optional<User>result=userrepository.findById(id);
	return result.get();
	}
	
	public User findByUsernameAndPassword(String username,String password) {
		return userrepository.findByUsernameAndPassword(username, password);
	}
	
	public List<User>search(String keyword){
		return userrepository.search(keyword);
	}

}
