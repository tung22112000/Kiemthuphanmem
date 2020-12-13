package com.greenacademy.bookstore.service;

import java.util.List;

import com.greenacademy.bookstore.entities.UserType;

public interface UserTypeService {

   
    void createDefaultUserTypes() throws Exception;
    
    List<UserType> getAll();
}