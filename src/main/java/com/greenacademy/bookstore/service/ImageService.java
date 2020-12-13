package com.greenacademy.bookstore.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadFile(String uploadRootPath, MultipartFile file);
}
