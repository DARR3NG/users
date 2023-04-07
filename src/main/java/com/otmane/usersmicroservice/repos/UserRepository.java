package com.otmane.usersmicroservice.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.otmane.usersmicroservice.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	List <User > findByNomUserContains(String nom);
	@Modifying
	@Query("update User u set u.enabled=?1 where u.user_id=?2")
	void changeStatut(Boolean statu,Long id);
	Page<User>findAll(Pageable pageable);


}
