package com.vortexBackend.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vortexBackend.test.domain.PaymentMethod;
import com.vortexBackend.test.repository.PaymentMethodRepository;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	public PaymentMethodRepository paymentMethodRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<PaymentMethod> findAll() {
		
		return paymentMethodRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public PaymentMethod save(PaymentMethod entity) throws Exception {
		if (entity == null) {
			throw new Exception("El PaymenthMethod es nulo");
		}
		return paymentMethodRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public PaymentMethod update(PaymentMethod entity) throws Exception {
		if (paymentMethodRepository.existsById(entity.getPayId()) == false) {
			throw new Exception("El paymenthMethod con payId: " + entity.getPayId() + " no existe");
		}
		return paymentMethodRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(PaymentMethod entity) throws Exception {
		if (entity == null) {
			throw new Exception("El PaymentMethod es nulo");
		}
		if (entity.getPayId() == null || entity.getPayId() < 0) {
			throw new Exception("El payId es obligatorio");
		}
		if (paymentMethodRepository.existsById(entity.getPayId()) == false) {
			throw new Exception("El paymentMethod con payId: " + entity.getPayId() + " no existe");
		}
		paymentMethodRepository.findById(entity.getPayId()).ifPresent(paymenthMethod -> {
			if (paymenthMethod.getShoppingCarts() != null && paymenthMethod.getShoppingCarts().isEmpty() == false) {
				throw new RuntimeException(
						"El paymentMethod con id: " + entity.getPayId() + " tiene ShoppingCarts, no se puede borrar");
			}
		});
		paymentMethodRepository.deleteById(entity.getPayId());
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Integer id) throws Exception {
		if (id == null || id < 0) {
			throw new Exception("El payId es obligatorio");
		}
		if (paymentMethodRepository.existsById(id)) {
			delete(paymentMethodRepository.findById(id).get());
		} else {
			throw new Exception("El paymentMethod con id: " + id + " no existe");
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PaymentMethod> findById(Integer id) throws Exception {
		return paymentMethodRepository.findById(id);
	}

	@Override
	public void validate(PaymentMethod entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

}
