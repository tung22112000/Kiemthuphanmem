package com.greenacademy.bookstore.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_type", catalog = "website")
public class UserType extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	
	private List<User> users;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_TYPE_ID", unique = true, nullable = false)
	public Long getId() {

		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "NAME", length = 30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="userType")
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}	
}
	

	

