package com.vortexBackend.test.controller;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortexBackend.test.domain.PaymentMethod;
import com.vortexBackend.test.dto.PaymentMethodDTO;
import com.vortexBackend.test.mapper.PaymentMethodMapper;
import com.vortexBackend.test.service.PaymentMethodService;





@RestController
@RequestMapping("/api/paymentMethod")
@CrossOrigin("*")
public class PaymentMethodRestController {
	@Autowired
	public PaymentMethodService paymentMethodService;

	@Autowired
	public PaymentMethodMapper paymentMethodMapper;
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception {
		List<PaymentMethod> paymentMethods = paymentMethodService.findAll();
		List<PaymentMethodDTO> paymentMethodDTOs = paymentMethodMapper.toPaymentMethodDTOs(paymentMethods);

		return ResponseEntity.ok().body(paymentMethodDTOs);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody PaymentMethodDTO paymentMethodDTO) throws Exception {
		PaymentMethod paymentMethod = paymentMethodMapper.toPaymentMethod(paymentMethodDTO);
		paymentMethod = paymentMethodService.save(paymentMethod);
		paymentMethodDTO = paymentMethodMapper.toPaymentMethodDTO(paymentMethod);
		return ResponseEntity.ok().body(paymentMethodDTO);
	}
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody PaymentMethodDTO paymentMethodDTO) throws Exception {
		PaymentMethod paymentMethod = paymentMethodMapper.toPaymentMethod(paymentMethodDTO);
		paymentMethod = paymentMethodService.update(paymentMethod);
		paymentMethodDTO = paymentMethodMapper.toPaymentMethodDTO(paymentMethod);	
		return ResponseEntity.ok().body(paymentMethodDTO);
	}
	@DeleteMapping("/delete/{payId}")
	public ResponseEntity<?> delete(@PathVariable("payId") Integer payId) throws Exception {
		paymentMethodService.deleteById(payId);

		return ResponseEntity.ok().build();
	}
	@GetMapping("/findById/{payId}")
	public ResponseEntity<?> findById(@PathVariable("payId") Integer payId) throws Exception {
		Optional<PaymentMethod> paymentMethodOptional = paymentMethodService.findById(payId);
		if (paymentMethodOptional.isPresent() == false) {
			return ResponseEntity.ok().body(null);
		}
		PaymentMethod paymentMethod = paymentMethodOptional.get();
		PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toPaymentMethodDTO(paymentMethod);

		return ResponseEntity.ok().body(paymentMethodDTO);
	}
}
