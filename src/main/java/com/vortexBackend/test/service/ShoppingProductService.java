package com.vortexBackend.test.service;

import java.util.List;

import com.vortexBackend.test.domain.ShoppingProduct;

public interface ShoppingProductService extends GenericService<ShoppingProduct, Integer> {

	public ShoppingProduct getShprByCarPro(Integer carId, String proId);
	
	public Long totalShoppingProductByShoppingCart(Integer carId);
	
	public Integer totalItems(Integer carId);
	
	
	public List<ShoppingProduct> findShprByCarId(Integer carId);
}
