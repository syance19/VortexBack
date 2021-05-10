package com.vortexBackend.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vortexBackend.test.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
