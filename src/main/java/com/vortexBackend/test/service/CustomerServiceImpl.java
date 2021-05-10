package com.vortexBackend.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vortexBackend.test.domain.Customer;
import com.vortexBackend.test.repository.CustomerRepository;

@Service

public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	public CustomerRepository customerRepository;
	

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		
		return customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Customer save(Customer entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		if (customerRepository.existsById(entity.getEmail())) {
			throw new Exception("El customer con id: " + entity.getEmail() + " ya existe");
		}
		return customerRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public Customer update(Customer entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		if (customerRepository.existsById(entity.getEmail()) == false) {
			throw new Exception("El customer con id: " + entity.getEmail() + " no existe");
		}

		return customerRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Customer entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		if (entity.getEmail() == null || entity.getEmail().trim().equals("") == true) {
			throw new Exception("El email es obligatorio");
		}
		if (customerRepository.existsById(entity.getEmail()) == false) {
			throw new Exception("El customer con id: " + entity.getEmail() + " no existe");
		}
		customerRepository.findById(entity.getEmail()).ifPresent(customer -> {
			if (customer.getShoppingCarts() != null && customer.getShoppingCarts().isEmpty() == false) {
				throw new RuntimeException(
						"El customer con id: " + entity.getEmail() + " tiene ShoppingCarts no se puede borrar");
			}
		});
		customerRepository.deleteById(entity.getEmail());
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(String id) throws Exception {
		if (id == null || id.isBlank() == true) {
			throw new Exception("El email es obligatorio");
		}

		if (customerRepository.existsById(id)) {
			delete(customerRepository.findById(id).get());
		} else {
			throw new Exception("El customer con id: " + id + " no existe");
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Customer> findById(String id) throws Exception {
		
		return customerRepository.findById(id);
	}

	@Override
	public void validate(Customer entity) throws Exception {
		
		
	}

	@Override
	public Long count() {
	
		return null;
	}

}
