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

import com.vortexBackend.test.domain.Product;
import com.vortexBackend.test.dto.ProductDTO;
import com.vortexBackend.test.mapper.ProductMapper;
import com.vortexBackend.test.service.ProductService;





@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductRestController {
	
	@Autowired
	ProductService productService;

	@Autowired
	ProductMapper productMapper;

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception {
		
		List<Product> products = productService.findAll();
		
		List<ProductDTO> productDTOs = productMapper.toProductDTOs(products);
		return ResponseEntity.ok().body(productDTOs);
	}
	
	
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ProductDTO productDTO) throws Exception {

		Product product = productMapper.toProduct(productDTO);
		product = productService.save(product);
		productDTO = productMapper.toProductDTO(product);
		
		return ResponseEntity.ok().body(productDTO);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update( @RequestBody ProductDTO productDTO) throws Exception {

		Product product = productMapper.toProduct(productDTO);
		product = productService.update(product);
		productDTO = productMapper.toProductDTO(product);

		return ResponseEntity.ok().body(productDTO);
	}
	@DeleteMapping("/delete/{proId}")
	public ResponseEntity<?> delete(@PathVariable("proId") String proId) throws Exception {
		productService.deleteById(proId);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/findById/{proId}")
	public ResponseEntity<?> findById(@PathVariable("proId") String proId) throws Exception {

		Optional<Product> productOptional = productService.findById(proId);
		if (productOptional.isPresent() == false) {
			return ResponseEntity.ok().body(null);
		}
		Product product = productOptional.get();
	
		ProductDTO productDTO = productMapper.toProductDTO(product);
		return ResponseEntity.ok().body(productDTO);
	}
	
	
	@GetMapping("/productLike/{name}")
	public ResponseEntity<?> findPorductLike(@PathVariable("name") String name) throws Exception {
	
		return ResponseEntity.ok().body(productMapper.toProductDTOs(productService.findByLikeName(name)));
	}

}
