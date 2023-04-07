package com.otmane.usersmicroservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration

@EnableWebSecurity
public class SecurityConfig {

	
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Bean
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).
		passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeHttpRequests().requestMatchers("/login").permitAll();
		http.authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/all").hasAuthority("ADMIN");
		http.authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/list/**").hasAuthority("ADMIN");
		http.authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/api/**").hasAuthority("ADMIN");
		http.authorizeHttpRequests().requestMatchers(HttpMethod.PUT,"/api/addroleTo/**").hasAuthority("ADMIN");
		http.authorizeHttpRequests().requestMatchers(HttpMethod.PUT,"/api/update/**").hasAuthority("ADMIN");
		http.authorizeHttpRequests().requestMatchers(HttpMethod.PUT,"/api/statut/**").hasAuthority("ADMIN");

		http.authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/api/UserByName/**").hasAuthority("ADMIN");
		http.authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/api/**").hasAuthority("ADMIN");
		http.authorizeHttpRequests().requestMatchers(HttpMethod.DELETE,"/api/**").hasAuthority("ADMIN");





		
		http.authorizeHttpRequests().anyRequest().authenticated();
		//http.formLogin();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}

	
	
}
