package com.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.modals.AuthRequest;
import com.gym.modals.GymUser;
import com.gym.service.GymUserService;
import com.gym.service.JwtService;

@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwtService;

	@Autowired
	private GymUserService service;

	@GetMapping("/home")
	public String getHome() {
		return "permit all endpoint";
	}

	@GetMapping("/user")
	public String getUser() {
		return "user endpoint";
	}

	@GetMapping("/admin")
	public String getAdmin() {
		return "admin endpoint";
	}

	// SignUp controller
	@PostMapping("/sign-up")
	public ResponseEntity<GymUser> signUp(@RequestBody GymUser gymUser) {
		GymUser userSignIn = service.getUserSignIn(gymUser);
		return new ResponseEntity<GymUser>(userSignIn, HttpStatus.OK);
	}

//	// SignUp controller
//	@PostMapping("/sign-in")
//	public ResponseEntity<GymUser> signIn(@RequestBody GymUser gymUser) {
//		GymUser userSignIn = service.getUser(gymUser);
//		return new ResponseEntity<GymUser>(userSignIn, HttpStatus.OK);
//	}

	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		
//		System.out.println("loggin in");
	
		System.out.println(authRequest.getUsername());
		System.out.println(authRequest.getPassword());
		
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		 Authentication authentication = null;
		try {
		    authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
		    );
		} catch (Exception e) {
		    System.out.println("Error during authentication: " + e.getMessage());
		    throw new UsernameNotFoundException("Invalid user request!");
		}
		
		System.out.println("token");
		
		if (authentication.isAuthenticated()) {
			System.out.println("logged in");
			String token = jwtService.generateToken(authRequest.getUsername());
			System.out.println("token");
			return token;
		} else {
			System.out.println("login un-successfull");
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

}
