package com.vortexBackend.test.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vortexBackend.test.domain.Product;
import com.vortexBackend.test.dto.ProductDTO;

@Mapper
public interface ProductMapper {

	public ProductDTO toProductDTO(Product product);

	public Product toProduct(ProductDTO productDTO);

	public List<ProductDTO> toProductDTOs(List<Product> products);

	public List<Product> toProducts(List<ProductDTO> productDTOs);

}
