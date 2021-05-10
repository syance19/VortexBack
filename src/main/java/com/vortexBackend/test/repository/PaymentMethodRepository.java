package com.vortexBackend.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vortexBackend.test.domain.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

}
