package com.greenacademy.bookstore.configration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenacademy.bookstore.service.UserTypeService;
import com.greenacademy.bookstore.service.impl.CategoryServiceImpl;
import com.greenacademy.bookstore.service.impl.ProductServiceImpl;
import com.greenacademy.bookstore.service.impl.PublisherServiceImpl;
import com.greenacademy.bookstore.service.impl.UserServiceImpl;

@Component
public class InitializationLoader {

	@Autowired
	private UserServiceImpl userService;
	/*
	 * @Autowired private PermissionServiceImpl permissonService;
	 */
	@Autowired
	private ProductServiceImpl productService;	
	@Autowired
	private CategoryServiceImpl categoryService;
	@Autowired
	private UserTypeService userTypeService;
	@Autowired
	private PublisherServiceImpl publisherService;
	
	@PostConstruct
	public void init() throws Exception {
		try {
			if (userService.getByUserName("admin") == null) {
//				permissonService.createDefaultPermissions();
				userService.createDefaultAdmin();
				userTypeService.createDefaultUserTypes();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createDefaultManager();
		createDefaultEmployee();
		createDefaultProduct();
		createDefaultCategory();
		createDefaultPublisher();
		

	}

	void createDefaultManager() {
		try {
			if (userService.getAllManagers().size() <= 0) {
				userService.createDefaultManager();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	void createDefaultEmployee() {
		try {
			if (userService.getAllEmployees().size() <= 0) {
				userService.createDefaultEmployee();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	void createDefaultProduct() {
		try {
			if(productService.getAll().size()<=0) {
				productService.createDefaultProduct();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void createDefaultCategory() {
		try {
			if(categoryService.getAll().size()<=0) {
				categoryService.createDefaultCategory();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void createDefaultPublisher() {
		try {
			if(publisherService.getAll().size()<=0) {
				publisherService.createDefaultPublisher();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
