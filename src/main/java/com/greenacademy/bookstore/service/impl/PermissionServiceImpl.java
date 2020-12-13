/*
 * package com.greenacademy.bookstore.service.impl;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.greenacademy.bookstore.entities.Permission; import
 * com.greenacademy.bookstore.repository.PermissionRepository; import
 * com.greenacademy.bookstore.service.PermissionService;
 * 
 * @Service public class PermissionServiceImpl implements PermissionService {
 * 
 * @Autowired private PermissionRepository permissionRepository;
 * 
 * public void createDefaultPermissions() { Permission permission1 = new
 * Permission(); permission1.setPermissionName("EMPLOYEE");
 * permissionRepository.save(permission1);
 * 
 * Permission permission2 = new Permission();
 * permission2.setPermissionName("MANAGER");
 * permissionRepository.save(permission2);
 * 
 * Permission permission3 = new Permission();
 * permission3.setPermissionName("SUPPER_ADMIN");
 * permissionRepository.save(permission3);
 * 
 * } public Permission getPermisionByName(String name) {
 * 
 * return permissionRepository.findByPermissionName(name); }
 * 
 * public Permission getPermisson(Long id) { Permission permission =
 * this.permissionRepository.getOne(id); return permission;
 * 
 * } public List<Permission> getAll() { return permissionRepository.findAll(); }
 * 
 * 
 * }
 */