package com.greenacademy.bookstore.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "publisher", schema = "website")
public class Publisher implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String publisherName;
	private String street;
	private String district;
	private String city;
	private String email;
	private String logo;
	private String phoneNumber;
	private MultipartFile imageFile = null;

	private List<Product> products;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PUBLISHER_ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PUBLISHER_NAME", length = 128)
	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	@Column(name = "STREET", length = 128)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "DISTRICT", length = 128)
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "CITY", length = 128)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "EMAIL", length = 128)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "NUMBER_PHONE", length = 11, nullable = false)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "LOGO", length = 128, nullable = true)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Transient
	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "publisher")
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", publisherName=" + publisherName + ", street=" + street + ", district="
				+ district + ", city=" + city + ", email=" + email + ", logo=" + logo + ", phoneNumber=" + phoneNumber
				+ ", imageFile=" + imageFile + ", products=" + products + "]";
	}

}
