package com.otmane.usersmicroservice.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.otmane.usersmicroservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);

}
