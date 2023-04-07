package com.otmane.usersmicroservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.otmane.usersmicroservice.model.Role;
import com.otmane.usersmicroservice.model.User;

public interface UserService {
	User saveUser(User user);
	User findUserByUsername(String username);
	Role addRole(Role role);
	User addRoleToUser(String username,String rolename);
	List<User>listUsers();
	User UpdateUser(User user);
	List<User> findByNomUserContains(String nom);
	
	List<Role>ListRole();
	User findUserById(Long id);
	void deleteUserById(Long id);
	void changeStatut(Boolean statut,Long id);
	Page<User>findPagination(Pageable pageable);




}
