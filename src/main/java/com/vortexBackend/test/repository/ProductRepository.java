package com.vortexBackend.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vortexBackend.test.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	@Query("SELECT p FROM Product p WHERE LOWER(p.name) like LOWER(concat('%', :name,'%'))")
	public List<Product> findByLikeName(String name);

}
