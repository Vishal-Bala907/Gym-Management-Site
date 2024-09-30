package com.gym.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gym.filter.JWTAuthFilter;
import com.gym.service.UserDetailsServiceImple;

@Configuration
public class SecurityConfiguratrion {

	@Autowired
	JWTAuthFilter jwtAuthFilter;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserDetailsServiceImple serviceImple;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.authorizeHttpRequests(req -> req.requestMatchers("/home", "/sign-up","/login").permitAll()
						.requestMatchers("/user").hasAnyRole("ADMIN", "USER").requestMatchers("/admin").hasRole("ADMIN")
						.anyRequest().authenticated())
				.csrf(csrf -> csrf.disable())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

		;

		return httpSecurity.build();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(serviceImple);
		authenticationProvider.setPasswordEncoder(encoder);
		return authenticationProvider;
	}

}
