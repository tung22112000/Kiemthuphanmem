package com.greenacademy.bookstore.service;

import java.util.List;

import com.greenacademy.bookstore.entities.Permission;

public interface PermissionService {
	public void createDefaultPermissions() ;
	
	public Permission getPermisionByName(String name);
	
	public Permission getPermisson(Long id);
	
	 public List<Permission> getAll();
}
