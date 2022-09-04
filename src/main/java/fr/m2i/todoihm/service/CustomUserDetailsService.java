package fr.m2i.todoihm.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fr.m2i.todoihm.model.Role;
import fr.m2i.todoihm.model.User;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserServiceImpl userService;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = Optional.ofNullable(userService.findByUsername(username))
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getGrantedAuthorities(user));
	}
	

	
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		
	
		Role role = Optional.ofNullable(user.getRole()).orElse(new Role());
		return Arrays.asList(new SimpleGrantedAuthority(role.getRoleName()));
	}

}
