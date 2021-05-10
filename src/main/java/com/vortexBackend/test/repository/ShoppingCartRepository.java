package com.vortexBackend.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vortexBackend.test.domain.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
	@Query("SELECT shca FROM ShoppingCart shca WHERE shca.customer.email=:email AND shca.paymentMethod.payId IS NULL")
	public List<ShoppingCart> findShcaByPayIdNull(String email);
}
