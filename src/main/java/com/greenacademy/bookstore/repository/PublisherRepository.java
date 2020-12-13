package com.greenacademy.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.bookstore.entities.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {

	@Query("select nxb from Publisher nxb where nxb.publisherName like %:publisherName%")
	List<Publisher> getFindPublisher(@Param("publisherName") String publisherName);
	
	@Query("select distinct p from Publisher as p where p.publisherName = :publisherName")
	Publisher findByPublisherName(@Param("publisherName") String publisherName);
}
