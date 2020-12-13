package com.greenacademy.bookstore.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.bookstore.entities.Permission;
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{
	 @Query("select distinct p from Permission as p where p.permissionName = :permissionName")
	    Permission findByPermissionName(@Param("permissionName") String permissionName);

}
