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
import org.springframework.test.annotation.Rollback;

import com.greenacademy.bookstore.entities.Category;
import com.greenacademy.bookstore.repository.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CategoryTest {
	@Autowired
	 private CategoryRepository categoryRepository;
	
	@Test
	@Rollback(false)
	public void testCreateCategory() {
		Category category1 = new Category();
		category1.setCategoryName("Java");
		category1.setCategoryImages("<i class=\"fab fa-adobe\"></i>");
		Category savedCategory = categoryRepository.save(category1);
		assertNotNull(savedCategory);
		
	}
	
	@Test
	public void testFindCategoryByNameExist() {
		String categoryName = "Dev";
		Category category = categoryRepository.findByCategoryName(categoryName);
		assertThat(category.getCategoryName()).isEqualTo(categoryName);
	}
	
	@Test
	public void testFindCategoryByNameNotExist() {
		String categoryName = "React";
		Category category = categoryRepository.findByCategoryName(categoryName);
		assertNull(category);
	}
	
	@Test
	public void testListCategory() {
		List<Category> categories = categoryRepository.findAll();
		for (Category c : categories) {
			System.out.println(c);
		}
		assertThat(categories).size().isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	public void testDelete() {
		long id = 5;
		boolean isExistBeforeDelete = categoryRepository.findById(id).isPresent();
		categoryRepository.deleteById(id);
		boolean notExistAfterDelete = categoryRepository.findById(id).isPresent();
		assertTrue(isExistBeforeDelete);
		assertFalse(notExistAfterDelete);
	}
	
	
	
}
