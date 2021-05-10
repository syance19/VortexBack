package com.vortexBackend.test.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vortexBackend.test.domain.Customer;
import com.vortexBackend.test.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {
	public CustomerDTO toCustomerDTO(Customer customer);

	public Customer toCustomer(CustomerDTO customerDTO);

	public List<CustomerDTO> toCustomersDTO(List<Customer> customers);

	public List<Customer> toCustomers(List<CustomerDTO> customersDTO);

}
