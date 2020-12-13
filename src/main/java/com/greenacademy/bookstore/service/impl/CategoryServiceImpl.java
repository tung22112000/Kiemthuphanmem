package com.greenacademy.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenacademy.bookstore.entities.Category;
import com.greenacademy.bookstore.repository.CategoryPagingAndSortingRepository;
import com.greenacademy.bookstore.repository.CategoryRepository;
import com.greenacademy.bookstore.service.CategoryService;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	 private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryPagingAndSortingRepository categoryPagingAndSortingRepository;
	public Category getCategory(Long id) {
		 Category category = categoryRepository.getOne(id);
		 return category;
	 }
	
	 public List<Category> getAll(){	 
		return this.categoryRepository.findAll();
		 
	 }
	
	 public String add(Category category) {
		 
		   String errorMessage="";
		  if (category.getCategoryName().isEmpty()) {
				errorMessage = "Vui lòng nhập tên danh mục!";

				return errorMessage;
			}
		  if (category.getCategoryImages().isEmpty()) {
				errorMessage = "Vui lòng nhập chọn 1 biểu tưởng danh mục ở đường link phía trên!";

				return errorMessage;
			}
		  this.categoryRepository.save(category);
		  return null;
	 }
	 
	 public String update(Category category) {
		 String errorMessage="";
		  if (category.getCategoryName().isEmpty()) {
				errorMessage = "Vui lòng nhập tên danh mục!";

				return errorMessage;
			}
		  if (category.getCategoryImages().isEmpty()) {
				errorMessage = "Vui lòng nhập chọn 1 biểu tưởng danh mục ở đường link phía trên!";

				return errorMessage;
			}
		  this.categoryRepository.save(category);
		  return null;
	 }
	 
	 public void delete(Category category) {
		 categoryRepository.delete(category);
	 }
	 
	 public void delete(Long id) {
		 Category category = categoryRepository.getOne(id);
		 categoryRepository.delete(category);
	 }
	 public List<Category> getFindCategory(String categoryName){
		 return categoryRepository.getFindCategory(categoryName);
	 }
	 public Page<Category> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
			Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			return this.categoryPagingAndSortingRepository.findAll(paging);
	 }

	@Override
	public void createDefaultCategory() throws Exception {
		Category category1 = new Category();
		category1.setCategoryName("Java");
		category1.setCategoryImages("<i class=\"fab fa-adobe\"></i>");
		categoryRepository.save(category1);
		
		Category category2 = new Category();
		category2.setCategoryName("Dev");
		category2.setCategoryImages("<i class=\"fab fa-adversal\"></i>");
		categoryRepository.save(category2);
		
		Category category3 = new Category();
		category3.setCategoryName("Java basic");
		category3.setCategoryImages("<i class=\"fab fa-amilia\"></i>");
		categoryRepository.save(category3);
		
	}
	 
}
