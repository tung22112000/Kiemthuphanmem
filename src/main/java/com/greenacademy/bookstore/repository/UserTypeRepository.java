package com.greenacademy.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.bookstore.entities.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long>{

}