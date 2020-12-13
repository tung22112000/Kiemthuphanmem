package com.greenacademy.bookstore.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.bookstore.entities.Category;
@Repository
public interface CategoryPagingAndSortingRepository extends PagingAndSortingRepository<Category, Long> {

}
