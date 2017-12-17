package com.zagorskidev.rescuecrm.entity.security;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

/**
 * Mapping object represents application user.
 * @author tomek
 *
 */
@Entity
@Table(name="user")
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="email")
	@Email(message="Invalid email")
	@NotEmpty(message="Invalid email")
	private String email;
	
	@Column(name="password")
	@Length(message="Password must have at least 5 characters")
	@NotEmpty(message="Password must have at least 5 characters")
	@Transient
	private String password;
	
	@Column(name="name")
	@NotEmpty(message="Invalid name")
	private String name;
	
	@Column(name="active")
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_role",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
}
