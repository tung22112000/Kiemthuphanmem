package com.greenacademy.bookstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenacademy.bookstore.entities.Category;
import com.greenacademy.bookstore.entities.Product;
import com.greenacademy.bookstore.repository.ProductPagingAndSortingRepository;
import com.greenacademy.bookstore.repository.ProductRepository;
import com.greenacademy.bookstore.service.ProductService;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private CategoryServiceImpl categoryService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductPagingAndSortingRepository productPagingAndSortingRepository;

	public Product getProduct(Long id) {
		Product product = productRepository.getOne(id);
		return product;
	}

	public List<Product> getAll() {
		return this.productRepository.findAll();

	}

	public String add(Product product) {
		String errorMessage = "";
		if (product.getProductName().isEmpty()) {
			errorMessage = "Vui lòng nhập tên sản phẩm!";

			return errorMessage;
		}
		if (product.getDiscount() == null) {
			errorMessage = "Vui lòng nhập phầm trăm giảm giá!";

			return errorMessage;
		}
		if (product.getDiscount() < 0) {
			errorMessage = "Vui lòng nhập phầm trăm giảm giá không được âm!";

			return errorMessage;
		}
		if (product.getOldPrice() == null) {
			errorMessage = "Vui lòng nhập giá cũ!";

			return errorMessage;
		}
		if (product.getOldPrice() < 0) {
			errorMessage = "Giá cũ không được nhỏ hơn 0!";

			return errorMessage;
		}
		if (product.getProductNumber() == null) {
			errorMessage = "Vui lòng nhập số lượng!";

			return errorMessage;
		}
		if (product.getProductNumber() < 0) {
			errorMessage = "Vui lòng nhập số lượng không được nhỏ hơn 0!";

			return errorMessage;
		}
		if (product.getYearManufactured() == null) {
			errorMessage = "Vui lòng nhập năm sản xuất!";

			return errorMessage;
		}

		if (product.getProductImages() == null || product.getProductImages().isEmpty()) {
			errorMessage = "Vui lòng chọn hình ảnh!";

			return errorMessage;
		}
		if (product.getDescription().isEmpty()) {
			errorMessage = "Vui lòng nhập mô tả!";

			return errorMessage;
		}
		if (product.getCategories().isEmpty()) {
			errorMessage = "Vui lòng chọn danh mục!";

			return errorMessage;
		}

		product.setCreated(new Date());
		this.productRepository.save(product);

		return null;
	}

	public String update(Product product) {
		String errorMessage = "";
		if (product.getProductName().isEmpty()) {
			errorMessage = "Vui lòng nhập tên sản phẩm!";

			return errorMessage;
		}
		if (product.getDiscount() == null) {
			errorMessage = "Vui lòng nhập phầm trăm giảm giá!";

			return errorMessage;
		}
		if (product.getDiscount() < 0) {
			errorMessage = "Vui lòng nhập phầm trăm giảm giá không được âm!";

			return errorMessage;
		}
		if (product.getOldPrice() == null) {
			errorMessage = "Vui lòng nhập giá cũ!";

			return errorMessage;
		}
		if (product.getOldPrice() < 0) {
			errorMessage = "Giá cũ không được nhỏ hơn 0!";

			return errorMessage;
		}
		if (product.getProductNumber() == null) {
			errorMessage = "Vui lòng nhập số lượng!";

			return errorMessage;
		}
		if (product.getProductNumber() < 0) {
			errorMessage = "Vui lòng nhập số lượng không được nhỏ hơn 0!";

			return errorMessage;
		}

		if (product.getProductImages() == null || product.getProductImages().isEmpty()) {
			errorMessage = "Vui lòng chọn hình ảnh!";

			return errorMessage;
		}
		if (product.getDescription().isEmpty()) {
			errorMessage = "Vui lòng nhập mô tả!";

			return errorMessage;
		}
		if (product.getYearManufactured() == null) {
			errorMessage = "Vui lòng nhập năm sản xuất!";

			return errorMessage;
		}

		if (product.getCategories().isEmpty()) {
			errorMessage = "Vui lòng chọn danh mục!";

			return errorMessage;
		}

		product.setCreated(new Date());
		this.productRepository.save(product);

		return null;
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

	public void delete(Long id) {
		Product product = productRepository.getOne(id);
		productRepository.delete(product);
	}

	public List<Product> getFindProduct(String productName) {
		return productRepository.getFindProduct(productName);
	}

	public List<Product> getAllByProduct(Pageable pageable) {
		return productRepository.getAllByProduct(pageable);
	}

	@Override
	public List<Product> getAllProductByCategory(Long id) {
		return productRepository.findByCategory(id);
	}

	public void createDefaultProduct() throws Exception {
		Product product = new Product();
		product.setProductName("Java");
		product.setOldPrice(12000);
		product.setDiscount((double) 20);
		product.setCreated(new Date());
		product.setProductNumber(30);
		product.setYearManufactured(2013);
		product.setDescription(
				"Cung cấp các kiến thức cơ bản về ngôn ngữ lập trình Java, kiến thức về lập trình hướng đối tượng, xử lý biệt lệ, lập trình đa luồng, lập trình from với swing và kết nối cơ sở dữ liệu với Java…");
		product.setProductImages("8.jpg");
		productRepository.save(product);

		Product product1 = new Product();
		product1.setProductName("Java căn bản");
		product1.setOldPrice(10000);
		product1.setDiscount((double) 15);
		product1.setCreated(new Date());
		product1.setProductNumber(20);
		product1.setYearManufactured(2016);
		product1.setDescription(
				"Nếu như bạn mong đợi người đời sẽ tử tế với bạn chỉ vì bạn tử tế với họ, thì cũng giống như việc bạn trông chờ một con sư tử không ăn thịt mình chỉ vì mình không ăn thịt nó. "
						+ "Cuộc sống vốn dĩ không công bằng, hãy học cách thích ứng đi.");
		product1.setProductImages("23.jpg");

		productRepository.save(product1);

		Product product2 = new Product();
		product2.setProductName("Development");
		product2.setOldPrice(20000);
		product2.setDiscount((double) 25);
		product2.setCreated(new Date());
		product2.setProductNumber(25);
		product2.setYearManufactured(2014);
		product2.setDescription("Bất kỳ một sự đả kích nào cũng không nên trở thành cái cớ cho bạn sa ngã. "
				+ "Bạn không thể thay đổi thế giới nhưng bạn có thể thay đổi bản thân mình. "
				+ "Việc cần làm là chọn lựa một con đường đúng đắn và kiên trì bước tiếp.");
		product2.setProductImages("21.jpg");
		productRepository.save(product2);

		Product product3 = new Product();
		product3.setProductName("Ngôn ngữ lập trình");
		product3.setOldPrice(20000);
		product3.setDiscount((double) 20);
		product3.setCreated(new Date());
		product3.setProductNumber(25);
		product3.setYearManufactured(2014);
		product3.setDescription(
				"Khi con người ta vẫn còn trên đời, tưởng rằng còn nhiều thời gian, nhiều cơ hội. "
				+ "Thực ra cuộc đời là phép trừ, gặp nhau một lần, ít đi một lần");
		product3.setProductImages("20.jpg");
		productRepository.save(product3);
		
		Product product4 = new Product();
		product4.setProductName("Kỹ nghệ phầm mền");
		product4.setOldPrice(20000);
		product4.setDiscount((double) 20);
		product4.setCreated(new Date());
		product4.setProductNumber(25);
		product4.setYearManufactured(2014);
		product4.setDescription(
				"Hãy cảm ơn những lúc bạn gặp khó khăn, bởi nếu không có khó khăn, bạn sẽ không có cơ hội để hiểu mình và trải nghiệm cuộc sống.");
		product4.setProductImages("22.jpg");
		productRepository.save(product4);
		
		Product product5 = new Product();
		product5.setProductName("Nhập môn đại cương");
		product5.setOldPrice(20000);
		product5.setDiscount((double) 20);
		product5.setCreated(new Date());
		product5.setProductNumber(25);
		product5.setYearManufactured(2014);
		product5.setDescription(
				"Cuộc sống có quyền đẩy bạn ngã nhưng ngồi đó than khóc hay đứng dậy và tiếp tục là quyền của bạn");
		product5.setProductImages("24.jpg");
		productRepository.save(product5);
		
		Product product6 = new Product();
		product6.setProductName("CSS and HTML");
		product6.setOldPrice(20000);
		product6.setDiscount((double) 20);
		product6.setCreated(new Date());
		product6.setProductNumber(25);
		product6.setYearManufactured(2014);
		product6.setDescription(
				"Người khác nói tới cách sống của bạn, cũng chẳng ảnh hưởng gì tới bạn."
				+ " Bạn sống thế nào, cũng không có liên quan gì đến người ta. "
				+ "Muốn có cuộc sống tốt đẹp thì đừng để tâm tới họ");
		product6.setProductImages("25.jpg");
		productRepository.save(product6);
		
		Product product7 = new Product();
		product7.setProductName("Toán cao cấp");
		product7.setOldPrice(20000);
		product7.setDiscount((double) 20);
		product7.setCreated(new Date());
		product7.setProductNumber(25);
		product7.setYearManufactured(2014);
		product7.setDescription(
				"Thời gian là miễn phí nhưng nó vô giá. Bạn không thể sở hữu nó, nhưng bạn có thể sử dụng nó. "
				+ "Bạn có thể dùng nó, nhưng bạn không thể giữ nó."
				+ " Một khi bạn làm mất nó, bạn sẽ không thể nào có lại được nó.");
		product7.setProductImages("26.png");
		productRepository.save(product7);
		
		Product product8 = new Product();
		product8.setProductName("Tư tưởng Hồ Chí Minh");
		product8.setOldPrice(20000);
		product8.setDiscount((double) 20);
		product8.setCreated(new Date());
		product8.setProductNumber(25);
		product8.setYearManufactured(2014);
		product8.setDescription(
				"Cuộc sống không phải luôn luôn hoàn hảo nhưng nó là kết quả của những gì bạn tạo ra. "
				+ "Vì thế hãy làm nó có giá trị, làm sao cho nó đáng nhớ và đừng bao giờ để ai đó đánh cắp hạnh phúc của bạn.");
		product8.setProductImages("27.jpg");
		productRepository.save(product8);
		
		Product product9 = new Product();
		product9.setProductName("Đường lối cách mạng");
		product9.setOldPrice(20000);
		product9.setDiscount((double) 20);
		product9.setCreated(new Date());
		product9.setProductNumber(25);
		product9.setYearManufactured(2014);
		product9.setDescription(
				"Cuộc sống không phải luôn luôn hoàn hảo nhưng nó là kết quả của những gì bạn tạo ra. "
				+ "Vì thế hãy làm nó có giá trị, làm sao cho nó đáng nhớ và đừng bao giờ để ai đó đánh cắp hạnh phúc của bạn.");
		product9.setProductImages("28.jpg");
		productRepository.save(product9);
		
		Product product10 = new Product();
		product10.setProductName("Thuật toán máy tính");
		product10.setOldPrice(20000);
		product10.setDiscount((double) 20);
		product10.setCreated(new Date());
		product10.setProductNumber(25);
		product10.setYearManufactured(2014);
		product10.setDescription(
				" Lo lắng chỉ làm lãng phí thời gian, nó không làm thay đổi gì cả, ngoài việc lấy đi niềm vui và làm cho bạn lúc nào cũng bận rộn mà chẳng hoàn tất được gì cả.");
		product10.setProductImages("php2.jpg");
		productRepository.save(product10);
		
		Product product11 = new Product();
		product11.setProductName("Ngôn ngữ C");
		product11.setOldPrice(20000);
		product11.setDiscount((double) 20);
		product11.setCreated(new Date());
		product11.setProductNumber(25);
		product11.setYearManufactured(2014);
		product11.setDescription(
				" Im lặng và mỉm cười là 2 vũ khí lợi hại. Mỉm cười là cách để giải quyết nhiều vấn đề , im lặng là cách để tránh những vấn đề rắc rối xảy ra");
		product11.setProductImages("29.jpg");
		productRepository.save(product11);
	}

	@Override
	public Page<Product> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return this.productPagingAndSortingRepository.findAll(paging);
	}

	@Override
	public Page<Product> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy, Long filter) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return this.productPagingAndSortingRepository.findAllWithFilter(filter, paging);
	}

	public Page<Product> getAllWithPaginationPublisher(Integer pageNo, Integer pageSize, String sortBy, Long filter) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return this.productPagingAndSortingRepository.findAllWithFilterPublisher(filter, paging);
	}

}
