package com.otmane.usersmicroservice.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otmane.usersmicroservice.model.Role;
import com.otmane.usersmicroservice.model.User;
import com.otmane.usersmicroservice.repos.RoleRepository;
import com.otmane.usersmicroservice.repos.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}

	@Override
	public User findUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

	@Override
	public Role addRole(Role role) {
		
		return roleRepository.save(role);
	}

	@Override
	public User addRoleToUser(String username, String rolename) {
		User usr=userRepository.findByUsername(username);
		Role role=roleRepository.findByRole(rolename);
		usr.getRoles().add(role);
		
		//avec la notation @Transac... save sera  faire automatiquement commiter la transaction cas d erreur tarbsaction sera annuler
		//userRepository.save(usr);
		
	return usr;
	}

	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}

	@Override
	public User UpdateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findByNomUserContains(String nom) {
		return userRepository.findByNomUserContains(nom);
	}

	@Override
	public List<Role> ListRole() {
		return roleRepository.findAll() ;
	}

	@Override
	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}
	
	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public void changeStatut(Boolean statut,Long id) {
		// TODO Auto-generated method stub
		 userRepository.changeStatut(statut,id);
		 
	}
	
	@Override
	public Page<User> findPagination(Pageable pageable) {
		//Pageable pageable=PageRequest.of(page - 1, pageSize);
		return this.userRepository.findAll(pageable);
	}

}
