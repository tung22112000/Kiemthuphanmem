package com.greenacademy.bookstore.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.bookstore.entities.Customer;
@Repository
public interface CustomerPagingAndSortingRepository extends PagingAndSortingRepository<Customer, Long> {

}
