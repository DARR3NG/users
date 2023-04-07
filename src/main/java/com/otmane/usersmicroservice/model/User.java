package com.otmane.usersmicroservice.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
	
	


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long user_id;
	
	private String nomUser;
	private String numTelUser;
	private String emailUser;
	
	@Column(unique = true)
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private Boolean enabled;
	
	
	@ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinTable(name = "user_role" , joinColumns = @JoinColumn (referencedColumnName = "user_id") , inverseJoinColumns = @JoinColumn(referencedColumnName = "role_id"))
	private Collection<Role> Roles = new ArrayList<>();

	/*@OneToMany(targetEntity = Profile.class,cascade =CascadeType.ALL )
	@JoinColumn(name = "idProfile" , referencedColumnName = "idProfile")
	private List<Profile>profiles;*/
}
