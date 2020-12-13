package com.greenacademy.bookstore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.greenacademy.bookstore.entities.Publisher;

public interface PublisherService {

	public  Publisher getPublisher(Long id);

    public List<Publisher> getAll();

    public String add(Publisher publisher);

    public String update(Publisher publisher);

    public void delete(Publisher publisher);

    public void delete(Long id);
    
    public void createDefaultPublisher() throws Exception;
    
    public List<Publisher> getFindPublisher(String publisherName);
    
    public Page<Publisher> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy);
}
