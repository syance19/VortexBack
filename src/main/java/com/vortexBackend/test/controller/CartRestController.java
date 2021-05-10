package com.vortexBackend.test.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortexBackend.test.domain.ShoppingCart;
import com.vortexBackend.test.domain.ShoppingProduct;
import com.vortexBackend.test.dto.AddShprDTO;
import com.vortexBackend.test.dto.EmailDTO;
import com.vortexBackend.test.dto.ShoppingCartDTO;
import com.vortexBackend.test.dto.ShoppingProductDTO;
import com.vortexBackend.test.mapper.ShoppingCartMapper;
import com.vortexBackend.test.mapper.ShoppingProductMapper;
import com.vortexBackend.test.service.CartService;





@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")

public class CartRestController {

	

	@Autowired
	CartService cartService;

	@Autowired
	ShoppingProductMapper shoppingProductMapper;

	@Autowired
	ShoppingCartMapper shoppingCartMapper;

	@PostMapping("/createCart")
	public ResponseEntity<?> createCart(@Valid @RequestBody EmailDTO emailDTO) throws Exception {

		ShoppingCart shoppingCart = cartService.createCart(emailDTO.getEmail());
		ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toShoppingCartDTO(shoppingCart);
		
		return ResponseEntity.ok().body(shoppingCartDTO);
	}

	

	@DeleteMapping("/clearCart/{carId}")
	public ResponseEntity<?> clearCart(@PathVariable("carId") Integer carId) throws Exception {
		cartService.clearCart(carId);
		;
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/findShcaByPayIdNull/{email}")
	public ResponseEntity<?> findShcaByPayIdNull(@PathVariable("email") String email) throws Exception {

		List<ShoppingCart> shoppingCarts = cartService.findShcaByPayIdNull(email);
		if (shoppingCarts.isEmpty() == true || shoppingCarts == null) {
			return ResponseEntity.ok().body(null);
		}
		List<ShoppingCartDTO> shoppingCartDTOs = shoppingCartMapper.toListShoppingCartDTO(shoppingCarts);

		return ResponseEntity.ok().body(shoppingCartDTOs);

	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@Valid @RequestBody AddShprDTO addShprDTO) throws Exception {

		ShoppingProduct shoppingProduct = cartService.addProduct(addShprDTO.getCarId(), addShprDTO.getProId(),
				addShprDTO.getQuantity());
		ShoppingProductDTO shoppingProductDTO = shoppingProductMapper.toShoppingProductDTO(shoppingProduct);
	
		return ResponseEntity.ok().body(shoppingProductDTO);
	}

	

	
	
	

}
