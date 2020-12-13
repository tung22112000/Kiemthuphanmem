package com.greenacademy.bookstore.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.bookstore.entities.Order;
@Repository
public interface OrderPagingAndSortingRepository extends PagingAndSortingRepository<Order, Long> {

}
