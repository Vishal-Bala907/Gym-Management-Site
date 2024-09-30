package com.gym.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gym.modals.GymUser;

@Service
public class UserService implements UserDetails {

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	private static final long serialVersionUID = 2226114157448536010L;

	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserService(GymUser gymUser) {
		super();
		this.username = gymUser.getUserName();
		this.password = gymUser.getPassword();
		this.authorities = Collections.singleton(new SimpleGrantedAuthority(gymUser.getRole()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

}
