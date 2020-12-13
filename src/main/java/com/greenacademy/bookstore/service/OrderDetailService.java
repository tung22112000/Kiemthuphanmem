package com.greenacademy.bookstore.service;

import java.util.List;

import com.greenacademy.bookstore.entities.OrderDetail;
import com.greenacademy.bookstore.model.CartInfo;

public interface OrderDetailService {
	/*
	 * public OrderDetail getOrderDetail(Long id);
	 * 
	 * public List<OrderDetail> getAll();
	 * 
	 * 
	 * public OrderDetail add(OrderDetail orderDetail);
	 * 
	 * public OrderDetail update(OrderDetail orderDetail);
	 * 
	 * public void delete(OrderDetail orderDetail);
	 * 
	 * public void delete(Long id);
	 * 
	 * public void saveOrder(CartInfo cartInfo);
	 */
	public OrderDetail getOrderDetail(Long id);

	public List<OrderDetail> getAll();

	public OrderDetail update(OrderDetail orderDetail);

	public void delete(Long id);

	public void delete(OrderDetail orderDetail);
}
