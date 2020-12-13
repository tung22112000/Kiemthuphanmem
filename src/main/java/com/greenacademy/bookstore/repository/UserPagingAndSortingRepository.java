package com.greenacademy.bookstore.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.bookstore.entities.User;

@Repository
public interface UserPagingAndSortingRepository extends PagingAndSortingRepository <User, Long >{

}
