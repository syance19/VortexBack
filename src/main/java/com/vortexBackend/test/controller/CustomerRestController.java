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

import com.vortexBackend.test.domain.Customer;
import com.vortexBackend.test.dto.CustomerDTO;
import com.vortexBackend.test.mapper.CustomerMapper;
import com.vortexBackend.test.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerRestController {

	@Autowired
	public CustomerService customerService;

	@Autowired
	public CustomerMapper customerMappper;

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception {

	
		List<Customer> customers = customerService.findAll();
		
		List<CustomerDTO> customerDTOs = customerMappper.toCustomersDTO(customers);
		return ResponseEntity.ok().body(customerDTOs);
	}
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody CustomerDTO customerDTO) throws Exception {

		Customer customer = customerMappper.toCustomer(customerDTO);
		customer = customerService.save(customer);
		customerDTO = customerMappper.toCustomerDTO(customer);
		return ResponseEntity.ok().body(customerDTO);
	}
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody CustomerDTO customerDTO) throws Exception {

		Customer customer = customerMappper.toCustomer(customerDTO);
		customer = customerService.update(customer);
		customerDTO = customerMappper.toCustomerDTO(customer);
		return ResponseEntity.ok().body(customerDTO);
	}
	
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<?> delete(@PathVariable("email") String email) throws Exception {

		customerService.deleteById(email);

		return ResponseEntity.ok().build();

	}
	@GetMapping("/findById/{email}")
	public ResponseEntity<?> findById(@PathVariable("email") String email) throws Exception {

		Optional<Customer> customerOptional = customerService.findById(email);
		if (customerOptional.isPresent() == false) {
			return ResponseEntity.ok().body(null);
		}

		Customer customer = customerOptional.get();

		CustomerDTO customerDTO = customerMappper.toCustomerDTO(customer);

		return ResponseEntity.ok().body(customerDTO);

	}
}
