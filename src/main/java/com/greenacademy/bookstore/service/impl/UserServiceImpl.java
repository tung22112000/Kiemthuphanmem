package com.greenacademy.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenacademy.bookstore.entities.Permission;
import com.greenacademy.bookstore.entities.User;
import com.greenacademy.bookstore.entities.UserType;
import com.greenacademy.bookstore.repository.UserPagingAndSortingRepository;
import com.greenacademy.bookstore.repository.UserRepository;
import com.greenacademy.bookstore.repository.UserTypeRepository;
import com.greenacademy.bookstore.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired 
	 private UserPagingAndSortingRepository userPagingAndSortingRepository;

	/*
	 * @Autowired private PermissionServiceImpl permissionService;
	 */
	private static final String DEFAULT_INITIAL_PASSWORD = "admin";

	@Autowired
	private UserTypeRepository userTypeReposity;
	
	public User getByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	public User getUser(Long id) {
		return userRepository.getOne(id);
	}
	public List<User> getAll() {
		return this.userRepository.findAll();
	}
	
	/*
	 * public List<User> getAllManagers(){ return
	 * userRepository.findByPermissionName("MANAGER"); }
	 * 
	 * public List<User> getAllEmployees(){ return
	 * userRepository.findByPermissionName("EMPLOYEE"); }
	 */
	
	public List<User> getAllManagers(){
		return userRepository.findAllManager();
	}

	public List<User> getAllEmployees(){
		return userRepository.findAlEmployee();
	}
	public String add( User user) {
		
		String errorMessage = "";
		

		if (user.getLastName().isEmpty() || user.getFirstName().isEmpty()) {
			errorMessage = "Họ tên không được bỏ trống";
			return errorMessage;
		}
	
		if (user.getEmail().isEmpty()) {
			errorMessage = "Email không được bỏ trống";
			return errorMessage;
		}
		/*
		 * if (user.getPermissions().isEmpty()) { errorMessage =
		 * "Vui lòng chọn phân quyền!";
		 * 
		 * return errorMessage; }
		 */
		if (user.getUserName().isEmpty()) {
			errorMessage = "Tên đăng nhập không được bỏ trống!";
			return errorMessage;
		}
		
		String password = passwordEncoder.encode(DEFAULT_INITIAL_PASSWORD);
		user.setPassword(password);
		user.setIsActive(true);
		
		
		
		this.userRepository.save(user);
		return null;
	}
	
	public String update( User user) {
		String errorMessage = "";

		if (user.getLastName().isEmpty() || user.getFirstName().isEmpty()) {
			errorMessage = "Họ tên không được bỏ trống";
			return errorMessage;
		}
	
		if (user.getEmail().isEmpty()) {
			errorMessage = "Email không được bỏ trống";
			return errorMessage;
		}
		/*
		 * if (user.getPermissions().isEmpty()) { errorMessage =
		 * "Vui lòng chọn phân quyền!";
		 * 
		 * return errorMessage; }
		 */
		if (user.getUserName().isEmpty()) {
			errorMessage = "Tên đăng nhập không được bỏ trống!";
			return errorMessage;
		}
		this.userRepository.save(user);
		return null;
	}
	public void delete( User user) {
		userRepository.delete(user);
	}
	public void delete(Long id) {
		User user = userRepository.getOne(id);
		userRepository.delete(user);
	}

	public void createDefaultAdmin() throws Exception {
//		Permission supperAdmin = permissionService.getPermisionByName("SUPPER_ADMIN");
		String password = passwordEncoder.encode(DEFAULT_INITIAL_PASSWORD);
		UserType adminUserType =userTypeReposity.getOne(1L);
		// creation of the super admin admin:password)
		User user = new User();
		user.setUserName("admin");
		user.setPassword(password);
		user.setEmail("admin@greenacedamy.com");
		user.setFirstName("Administrator");
		user.setLastName("User");
		user.setIsActive(true);
		user.setUserType(adminUserType);

//		List<Permission> permissions = new ArrayList<Permission>();
//		permissions.add(supperAdmin);
//		user.setPermissions(permissions);

		userRepository.save(user);

	}

	public void createDefaultManager() throws Exception{
//		Permission manager = permissionService.getPermisionByName("MANAGER");
		String password = passwordEncoder.encode(DEFAULT_INITIAL_PASSWORD);
		UserType adminUserType =userTypeReposity.getOne(2L);
		// creation of the super admin admin:password)
		User user = new User();
		user.setUserName("manager1");
		user.setPassword(password);
		user.setEmail("manager1@greenacedamy.com");
		user.setFirstName("Manager");
		user.setLastName("User");
		user.setIsActive(true);
		user.setUserType(adminUserType);

//		List<Permission> permissions = new ArrayList<Permission>();
//		permissions.add(manager);
//		user.setPermissions(permissions);

		userRepository.save(user);
	}

	public void createDefaultEmployee() throws Exception {
//		Permission employee = permissionService.getPermisionByName("EMPLOYEE");
		String password = passwordEncoder.encode(DEFAULT_INITIAL_PASSWORD);
		UserType adminUserType =userTypeReposity.getOne(3L);
		// creation of the super admin admin:password)
		User user = new User();
		user.setUserName("employee1");
		user.setPassword(password);
		user.setEmail("employee1@greenacedamy.com");
		user.setFirstName("Employee");
		user.setLastName("User");
		user.setIsActive(true);
		user.setUserType(adminUserType);
//		List<Permission> permissions = new ArrayList<Permission>();
//		permissions.add(employee);
//		user.setPermissions(permissions);

		userRepository.save(user);
	}
	
	public boolean changePassword(String username, String oldpass, String newpass) 
	{		
		User user = this.userRepository.findByUserName(username);
		if (user != null )
		{
			if (passwordEncoder.matches(oldpass, user.getPassword()))
			{
				String newpassword = passwordEncoder.encode(newpass);
				user.setPassword(newpassword);
				userRepository.save(user);
				return true;	
			}
		}
		return false;
		
	}
	public String checkUserInfo(String username, String phone, String email){
		User user = this.userRepository.findByUserNameEmailPhone(username,phone,email);
		if (user == null) {
			return "Thông tin nhập không đúng. Vui lòng kiểm tra lại";
		} 
		
		return null;
	}
	public String checkUserInfo(String username) {
		User user = this.userRepository.findByUserName(username);
		if(user != null) {
			return "Tài khoản đã tồn tại";
		}
		return null;
	}
	public Page<User> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return this.userPagingAndSortingRepository.findAll(paging);
	}
	

	public List<User> searchByUserName(String userName) {
		return  this.userRepository.findByUserName1(userName);
	}
	@Override
	public String changePassword1(String userName, String newPassword, String retypePassword) {
		User user = this.userRepository.findByUserName(userName);
		if (user != null) {
			
			if(newPassword.equals(retypePassword) == false ) {
				return "Mật khẩu xác nhận không khớp";
			}
			if(newPassword.length() <= 5) {
				return "Mật khẩu quá ngắn";
			}
			
			String password = passwordEncoder.encode(newPassword);
			user.setPassword(password);
			userRepository.save(user);
			System.out.println("update thanh cong");
			
		}
		return null;
	}
	@Override
	public List<User> searchUserByUserNameAndPhoneNumber(String userNameandPhonenumber) {
		return this.userRepository.findUserByUserNameAndPhoneNumber(userNameandPhonenumber);
	}
	@Override
	public List<User> findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
