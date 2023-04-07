package com.otmane.usersmicroservice.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otmane.usersmicroservice.model.Role;
import com.otmane.usersmicroservice.model.User;
import com.otmane.usersmicroservice.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserRESTController {

		@Autowired
		UserService userService;
		
		@GetMapping("/all")
		public List<User>getAllUsers(){
			return userService.listUsers();
		}
		@GetMapping("/list")
		public Page<User>showPage(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int pageSize){
			Pageable paging = PageRequest.of(page, pageSize);
			return userService.findPagination(paging);
		} 
		
		@PostMapping("")
		public User addUser(@RequestBody User usr) {
			return userService.saveUser(usr);
		}
		
		@PutMapping("/addroleTo/{username}/{role}")
		public User addroleToUser(@PathVariable("username") String username,@PathVariable("role") String rolename) {
			return userService.addRoleToUser(username, rolename);
		}
		
		@PutMapping("/update")
		public User updateUser(@RequestBody User user) {
			return userService.UpdateUser(user);
			
		}
		@GetMapping("/UserByName/{nom}")
		public List<User>findByNomUserContains(@PathVariable("nom") String nom){
			return userService.findByNomUserContains(nom);
		}
		
		@GetMapping("/Roles/all")
		public List<Role>findAllRoles(){
			return userService.ListRole();
		}
		
		@GetMapping("/{id}")
		public User findById(@PathVariable("id")Long id){
			return userService.findUserById(id);
		}
		
		@DeleteMapping("/{id}")
		public void deleteProduit(@PathVariable("id") Long id) {
			userService.deleteUserById(id);
		}
		@PutMapping("/statut/{id}/{statut}")
		public void changeStatut(@PathVariable("statut") Boolean statut,  @PathVariable("id") Long id) {
			
				
			 userService.changeStatut(statut, id);
		}
		
		
}
