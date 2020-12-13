package com.greenacademy.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.greenacademy.bookstore.entities.Category;
import com.greenacademy.bookstore.entities.Product;
import com.greenacademy.bookstore.repository.CategoryRepository;
import com.greenacademy.bookstore.repository.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	@Rollback(false)
	public void testCreateProduct() {
		Product product = new Product();
		product.setProductName("ReactJs");
		product.setOldPrice(25000);
		product.setDiscount((double) 20);
		product.setCreated(new Date());
		product.setProductNumber(30);
		product.setYearManufactured(2018);
		product.setDescription("Cung cấp các kiến thức cơ bản về thiết kế UI cho web");
		product.setProductImages("reactjs.jpg");
		Product savedProduct = productRepository.save(product);

		assertNotNull(savedProduct);
	}

	@Test
	public void testFindProductByNameExist() {
		String productName = "Java";
		Product product = (Product) productRepository.getFindProduct(productName);
		assertThat(product.getProductName()).isEqualTo(productName);
	}

	@Test
	public void testFindProductByNameNotExist() {
		String productName = "React";
		Product product = (Product) productRepository.getFindProduct(productName);
		assertNull(product);
	}

	@Test
	public void testList() {
		List<Product> products = productRepository.findAll();
		for (Product p : products) {
			System.out.println(p);
		}
		assertThat(products).size().isGreaterThan(0);
	}

	@Test
	@Rollback(false)
	public void testDelete() {
		long id = 5;
		boolean isExistBeforeDelete = productRepository.findById(id).isPresent();
		productRepository.deleteById(id);
		boolean notExistAfterDelete = productRepository.findById(id).isPresent();
		assertTrue(isExistBeforeDelete);
		assertFalse(notExistAfterDelete);
	}
}
