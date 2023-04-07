package com.otmane.usersmicroservice.security;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.otmane.usersmicroservice.model.User;
import com.otmane.usersmicroservice.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userService.findUserByUsername(username);
		
		if(user==null || user.getEnabled()==false)
			throw new UsernameNotFoundException("Utilisateur introuvable");
		
		Collection <GrantedAuthority> auths=new ArrayList<>();
		
		user.getRoles().forEach(role->{
			GrantedAuthority authority=new SimpleGrantedAuthority(role.getRole());
			auths.add(authority);
		});
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), auths);
	}

}
