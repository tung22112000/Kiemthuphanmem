package com.greenacademy.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.bookstore.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	/*
	 * @Query("SELECT c FROM Customer c WHERE c.lastName = :lastName")
	 * List<Customer> findByLastNameLike(@Param("lastName") String lastName);
	 */
	
	@Query("select c from Customer as c where c.fullName like %:enter% or c.numberPhone like %:enter%")
	List<Customer> findCustomerByFullNameAndPhoneNumber(@Param("enter") String enter);
	
	@Query("select distinct c from Customer as c where  c.numberPhone = :phoneNumber")
	Customer findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
