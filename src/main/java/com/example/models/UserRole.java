package com.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
@Table(name ="user_role")
public class UserRole {
	
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int role_id;
	
	@Column(name="role")
	private String role;
	
	public UserRole() {
		super();
	}
	

	public UserRole(String role, int role_id) {
		super();
		this.role = role;
		this.role_id = role_id;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}


	@Override
	public String toString() {
		return "UserRole [role_id=" + role_id + ", role=" + role + "]";
	}
	
	
}
 