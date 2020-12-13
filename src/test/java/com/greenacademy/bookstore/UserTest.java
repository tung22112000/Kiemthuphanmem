package com.greenacademy.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.greenacademy.bookstore.entities.Category;
import com.greenacademy.bookstore.entities.User;
import com.greenacademy.bookstore.entities.UserType;
import com.greenacademy.bookstore.repository.CategoryRepository;
import com.greenacademy.bookstore.repository.UserRepository;
import com.greenacademy.bookstore.repository.UserTypeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserTest {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private static final String DEFAULT_INITIAL_PASSWORD = "admin";
	
	@Autowired
	private UserTypeRepository userTypeReposity;
	
	@Test
	@Rollback(false)
	public void testCreate() {
		String password = passwordEncoder.encode(DEFAULT_INITIAL_PASSWORD);
		UserType adminUserType =userTypeReposity.getOne(1L);
		// creation of the super admin admin:password)
		User user = new User();
		user.setUserName("admin1");
		user.setPassword(password);
		user.setEmail("admin1@greenacedamy.com");
		user.setFirstName("Administrator");
		user.setLastName("User");
		user.setIsActive(true);
		user.setUserType(adminUserType);

//		List<Permission> permissions = new ArrayList<Permission>();
//		permissions.add(supperAdmin);
//		user.setPermissions(permissions);

		User savedUser = userRepository.save(user);
		assertNotNull(savedUser);
		
	}
	
	@Test
	public void testFindUserByNameExist() {
		String userName = "admin";
		User user = userRepository.findByUserName(userName);
		assertThat(user.getUserName()).isEqualTo(userName);
	}
	
	@Test
	public void testFindCategoryByNameNotExist() {
		String userName = "admin1";
		User user = userRepository.findByUserName(userName);
		assertNull(user);
	}
	
	@Test
	public void testList() {
		List<User> users = userRepository.findAll();
		for (User u : users) {
			System.out.println(u);
		}
		assertThat(users).size().isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	public void testDelete() {
		long id = 3;
		boolean isExistBeforeDelete = userRepository.findById(id).isPresent();
		userRepository.deleteById(id);
		boolean notExistAfterDelete = userRepository.findById(id).isPresent();
		assertTrue(isExistBeforeDelete);
		assertFalse(notExistAfterDelete);
	}
}
