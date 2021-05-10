package com.vortexBackend.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexBackend.test.domain.Product;
import com.vortexBackend.test.repository.ProductRepository;

@Service
@Scope("singleton")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	public ProductRepository productRepository;
	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		
		return productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Product save(Product entity) throws Exception {
		if (entity == null) {
			throw new Exception("El product es nulo");
		}
		

		return productRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public Product update(Product entity) throws Exception {
		if (entity == null) {
			throw new Exception("El product es nulo");
		}
		return productRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Product entity) throws Exception {
		if (entity == null) {
			throw new Exception("El product es nulo");
		}
		if (entity.getProId() == null || entity.getProId().isBlank() == true) {
			throw new Exception("El proId es obligatorio");
		}

	
		if (productRepository.existsById(entity.getProId()) == false) {
			throw new Exception("El product con id: " + entity.getProId() + " no existe");
		}

	
		productRepository.findById(entity.getProId()).ifPresent(product -> {
			if (product.getShoppingProducts() != null && product.getShoppingProducts().isEmpty() == false) {
				throw new RuntimeException(
						"El product con id: " + entity.getProId() + " tiene ShoppingProducts no se puede borrar");
			}
		});

		productRepository.deleteById(entity.getProId());
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		if (id == null || id.isBlank() == true) {
			throw new Exception("El proId es obligatorio");
		}

		if (productRepository.existsById(id)) {
			delete(productRepository.findById(id).get());
		}else {
			throw new Exception("El product con id: " + id + " no existe");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> findById(String id) throws Exception {
		return productRepository.findById(id);
	}

	@Override
	public void validate(Product entity) throws Exception {	
	}

	@Override
	public Long count() {
		return null;
	}

	@Override
	public List<Product> findByLikeName(String name) throws Exception {
		return productRepository.findByLikeName(name);
	}

}
