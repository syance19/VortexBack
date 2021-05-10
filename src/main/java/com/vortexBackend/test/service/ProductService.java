package com.vortexBackend.test.service;

import java.util.List;

import com.vortexBackend.test.domain.Product;

public interface ProductService extends GenericService<Product, String> {
	public List<Product> findByLikeName(String name) throws Exception;

}
