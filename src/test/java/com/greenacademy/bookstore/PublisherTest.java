package com.greenacademy.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.greenacademy.bookstore.entities.Publisher;
import com.greenacademy.bookstore.repository.PublisherRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PublisherTest {
	@Autowired
	private PublisherRepository publisherRepository;

	@Test
	@Rollback(false)
	public void testCreate() {
		Publisher publisher= new Publisher();
		publisher.setPublisherName("NXB Kim Đồng");
		publisher.setLogo("logokimdong.pnp");
		publisher.setPhoneNumber("0234565432");
		publisher.setEmail("kimdonginfo@gmail.com");
		publisher.setStreet("50 Quang Trung");
		publisher.setDistrict("Tân Bình");
		publisher.setCity("Tp.HCM");
		publisherRepository.save(publisher);
		Publisher savedPublisher = publisherRepository.save(publisher);
		assertNotNull(savedPublisher);

	}

	@Test
	public void testFindPublisherByNameExist() {
		String publisherName = "NXB Kim Đồng";
		Publisher publisher = publisherRepository.findByPublisherName(publisherName);
		assertThat(publisher.getPublisherName()).isEqualTo(publisherName);
	}

	@Test
	public void testFindPublisherByNameNotExist() {
		String publisherName = "TVM";
		Publisher publisher = publisherRepository.findByPublisherName(publisherName);
		assertNull(publisher);
	}

	@Test
	public void testList() {
		List<Publisher> publishers = publisherRepository.findAll();
		for (Publisher p : publishers) {
			System.out.println(p);
		}
		assertThat(publishers).size().isGreaterThan(0);
	}

	@Test
	@Rollback(false)
	public void testDelete() {
		long id = 4;
		boolean isExistBeforeDelete = publisherRepository.findById(id).isPresent();
		publisherRepository.deleteById(id);
		boolean notExistAfterDelete = publisherRepository.findById(id).isPresent();
		assertTrue(isExistBeforeDelete);
		assertFalse(notExistAfterDelete);
	}
}
