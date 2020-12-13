package com.greenacademy.bookstore.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenacademy.bookstore.entities.Customer;
import com.greenacademy.bookstore.entities.Order;
import com.greenacademy.bookstore.entities.OrderDetail;
import com.greenacademy.bookstore.entities.Product;
import com.greenacademy.bookstore.model.CartInfo;
import com.greenacademy.bookstore.model.CartLineInfo;
import com.greenacademy.bookstore.model.CustomerInfo;
import com.greenacademy.bookstore.repository.CustomerRepository;
import com.greenacademy.bookstore.repository.OrderDetailRepository;
import com.greenacademy.bookstore.repository.OrderPagingAndSortingRepository;
import com.greenacademy.bookstore.repository.OrderRepository;
import com.greenacademy.bookstore.repository.ProductRepository;
import com.greenacademy.bookstore.service.OrderService;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderPagingAndSortingRepository orderPagingAndSortingRepository;

	public Order getOrder(Long id) {
		Order order = orderRepository.getOne(id);
		return order;
	}

	public List<Order> getAll() {
		return this.orderRepository.findAll();

	}

	public Order add(Order order) {
		return this.orderRepository.save(order);
	}

	public Order update(Order order) {
		return this.orderRepository.save(order);
	}

	public void delete(Order order) {
		orderRepository.delete(order);
	}

	public void delete(Long id) {
		Order order = orderRepository.getOne(id);
		orderRepository.delete(order);
	}

	@Override
	public List<OrderDetail> listOrderDetailInfos(Long orderId) {
		return this.orderDetailRepository.findOrderDetails(orderId);
	}
/////////////////////////////////////////////////////////////////////////////////////////

	public Order addOrder(CartInfo cartInfo) {
		Order order = new Order();
		/* OrderDetail orderDetail = new OrderDetail(); */

		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		String getPhoneNumber = customerInfo.getNumberPhone();
		Customer customerFind = customerRepository.findByPhoneNumber(getPhoneNumber);
		Customer customer = null;
		if (customerFind != null) {		
			customer = customerFind;
		} else {
			
			customer = new Customer();
			customer.setFirstName(customerInfo.getFirstName());
			customer.setLastName(customerInfo.getLastName());
			customer.setFullName(customer.getLastName() + " " + customer.getFirstName());
			customer.setEmail(customerInfo.getEmail());
			customer.setNumberPhone(customerInfo.getNumberPhone());
			customer.setStreet(customerInfo.getStreet());
			customer.setDistrict(customerInfo.getDistrict());
			customer.setCity(customerInfo.getCity());
			customerRepository.save(customer);			

		}
		

		java.util.Date today = new java.util.Date();
		int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int m = Calendar.getInstance().get(Calendar.MINUTE);
		
		order.setOrderDate(new java.sql.Date(today.getTime()));
		order.setHour(h);
		order.setMinute(m);
		order.setAmount(cartInfo.getAmountTotal());
		order.setCustomer(customer);
		order.setTotalQuanity(cartInfo.getQuantityTotal());
		
		orderRepository.save(order);

		List<CartLineInfo> lines = cartInfo.getCartLines();

		for (CartLineInfo line : lines) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setTotalPrice(line.getAmount());
			detail.setPrice(line.getProductInfo().getNewPrice());
			detail.setQuanity(line.getQuantity());
			detail.setTotalQuanity(cartInfo.getQuantityTotal());

			Long code = line.getProductInfo().getId();
			Product product = this.productRepository.getOne(code);
			detail.setProduct(product);
			orderDetailRepository.save(detail);
			
				product.setRemain(product.getProductNumber()- detail.getQuanity());
				product.setProductNumber(product.getRemain());
			
			
			
			
		}

		// Set OrderNum for report.
		// Set OrderNum để thông báo cho người dùng.
		cartInfo.setOrderNum(order.getId());
		return order;
	}

	@Override
	public List<Order> getFindOrder(Long id) {
		return orderRepository.getFindOrder(id);
	}

	@Override
	public List<Order> searchByFromDateAndToDate(java.util.Date fromDate, java.util.Date toDate) {

		List<Order> orders = orderRepository.searchByFromDateAndToDate(fromDate, toDate);
		return orders;
	}

	@Override
	public Page<Order> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return this.orderPagingAndSortingRepository.findAll(paging);
	}

	@Override
	public List<Order> searchOrderByFirstnameAndLastname(String name) {
		// TODO Auto-generated method stub
		return this.orderRepository.searchOrderByFirstnameAndLastname(name);
	}

	@Override
	public List<Order> searchByFromDateAndTimeToDateAndTime(java.util.Date fromDate, int fromHour, int fromMinute,
			java.util.Date toDate, int toHour, int toMinute) {
		List<Order> orders = orderRepository.searchByDatesAndTime(fromDate, fromHour, fromMinute, toDate, toHour, toMinute);
		return orders;
	}

	@Override
	public List<Order> searchByHoursAndMinute(int fromHour, int fromMinute, int toHour, int toMinute) {
		List<Order> orders = orderRepository.searchByHoursAndMinute(fromHour, fromMinute, toHour, toMinute);
		return orders;
	}

	@Override
	public List<Order> searchByDateAndTime(Date Date, int Hour, int Minute) {
		List<Order> orders  = orderRepository.searchByDateAndTime(Date, Hour, Minute);
		return orders;
	}




}
