package com.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gym.modals.GymUser;
import com.gym.repos.GymUserRepository;

@Service
public class UserDetailsServiceImple implements UserDetailsService {

	@Autowired
	GymUserRepository gymUserRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		GymUser byUserName = gymUserRepository.findByUserName(username);
		UserService service = new UserService(byUserName);
//		System.out.println("helo user");
		return service;
	}

}
