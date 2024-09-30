package com.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gym.modals.GymUser;
import com.gym.repos.GymUserRepository;

@Service
public class GymUserService {
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	GymUserRepository gymUserRepository;
	
	public GymUser getUserSignIn(GymUser gymUser) {
		gymUser.setPassword(encoder.encode(gymUser.getPassword()));
		gymUser.setRole("ROLE_USER");
		GymUser save = gymUserRepository.save(gymUser);
		return save;
		
	}

	public GymUser getUser(GymUser gymUser) {
		GymUser save = gymUserRepository.findByUserName(gymUser.getUserName());
		return save;
	} 

}
