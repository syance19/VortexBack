package com.vortexBackend.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vortexBackend.test.domain.ShoppingProduct;
import com.vortexBackend.test.repository.ShoppingProductRepository;

@Service
public class ShoppingProdutServiceImpl implements ShoppingProductService {

	@Autowired
	public ShoppingProductRepository shoppingProductRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> findAll() {
		return shoppingProductRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public ShoppingProduct save(ShoppingProduct entity) throws Exception {
		if (entity == null) {
			throw new Exception("El ShoppingProduct es nulo");
		}

		if (shoppingProductRepository.existsById(entity.getShprId())) {
			throw new Exception("El ShoppingProduct con id:" + entity.getShprId() + " Ya existe");
		}
		return shoppingProductRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public ShoppingProduct update(ShoppingProduct entity) throws Exception {
		if (entity == null) {
			throw new Exception("El ShoppingProduct es nulo");
		}
		if (shoppingProductRepository.existsById(entity.getShprId()) == false) {
			throw new Exception("El ShoppingProduct con id:" + entity.getShprId() + " No existe");
		}

		return shoppingProductRepository.save(entity);
	}

	@Override
	public void delete(ShoppingProduct entity) throws Exception {

		if (entity == null) {
			throw new Exception("El ShoppingProduct es nulo");
		}

		if (entity.getShprId() == null) {
			throw new Exception("El ShoppingProduct id es nulo");
		}

		if (shoppingProductRepository.existsById(entity.getShprId()) == false) {
			throw new Exception("El ShoppingProduct con id:" + entity.getShprId() + " No existe");
		}

		shoppingProductRepository.deleteById(entity.getShprId());
		
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		if (id == null) {
			throw new Exception("El ShoppingProduct id es nulo");
		}

		if (shoppingProductRepository.existsById(id)) {
			delete(shoppingProductRepository.findById(id).get());
		}
		
	}

	@Override
	public Optional<ShoppingProduct> findById(Integer id) throws Exception {
		return shoppingProductRepository.findById(id);
	}

	@Override
	public void validate(ShoppingProduct entity) throws Exception {
		
		
	}

	@Override
	public Long count() {
	
		return null;
	}

	@Override
	public ShoppingProduct getShprByCarPro(Integer carId, String proId) {
		return shoppingProductRepository.getShprByCarPro(carId, proId);
	}

	@Override
	@Transactional(readOnly = true)
	public Long totalShoppingProductByShoppingCart(Integer carId) {
		return shoppingProductRepository.totalShoppingProductByShoppingCart(carId);
	}

	@Override
	public Integer totalItems(Integer carId) {
		return shoppingProductRepository.totalItems(carId);
	}

	@Override
	public List<ShoppingProduct> findShprByCarId(Integer carId) {
		
		return shoppingProductRepository.findShprByCarId(carId);
	}



}
