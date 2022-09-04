package fr.m2i.todoihm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.m2i.todoihm.model.User;
import fr.m2i.todoihm.repository.UserRepository;


@Component
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository; 
	
	
	@Override
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

}
