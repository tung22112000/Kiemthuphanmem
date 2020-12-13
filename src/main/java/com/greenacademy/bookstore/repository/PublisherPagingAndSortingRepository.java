package com.greenacademy.bookstore.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.greenacademy.bookstore.entities.Publisher;

public interface PublisherPagingAndSortingRepository extends PagingAndSortingRepository<Publisher, Long> {

}
