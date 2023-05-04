package com.greatlearning.ems.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greatlearning.ems.entity.User;
import com.greatlearning.ems.repository.UserRepository;

public class EmsUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User userEntityObj = userRepository.findUserByUsername(username);
		EmsUserDetails userDetails = new EmsUserDetails(userEntityObj);

		return userDetails;
	}

}
