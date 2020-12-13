package com.greenacademy.bookstore.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.greenacademy.bookstore.entities.Order;
import com.greenacademy.bookstore.entities.OrderDetail;
import com.greenacademy.bookstore.model.CartInfo;

public interface OrderService {
	public Order getOrder(Long id);

	public Order addOrder(CartInfo cartInfo);

	public List<Order> getAll();

	public Order update(Order order);

	public void delete(Long id);

	public void delete(Order order);
	
	public List<OrderDetail> listOrderDetailInfos(Long orderId);
	
	public List<Order> getFindOrder(Long id);
	
	public List<Order> searchByFromDateAndToDate(Date fromDate, Date toDate);
	
	
	public List<Order> searchByHoursAndMinute(int fromHour,int fromMinute,int toHour,int toMinute);
	
	public List<Order> searchByDateAndTime(Date Date,int Hour,int Minute);
	
	public List<Order> searchByFromDateAndTimeToDateAndTime(Date fromDate,int fromHour,int fromMinute,Date toDate, int toHour,int toMinute);
	
	public List<Order>  searchOrderByFirstnameAndLastname(String name);
	
	public Page<Order> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy);
    
}