package com.vortexBackend.test.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vortexBackend.test.domain.ShoppingProduct;
import com.vortexBackend.test.dto.ShoppingProductDTO;

@Mapper
public interface ShoppingProductMapper {
	@Mapping(source = "product.proId", target = "productId")
	@Mapping(source = "shoppingCart.carId", target = "shoppingCartId")
	@Mapping(source = "product.name", target = "productName")
	@Mapping(source = "product.image", target = "productImage")
	@Mapping(source = "product.price", target = "productPrice")
	public ShoppingProductDTO toShoppingProductDTO(ShoppingProduct shoppingProduct);

	@Mapping(source = "productId", target = "product.proId")
	@Mapping(source = "shoppingCartId", target = "shoppingCart.carId")
	@Mapping(source = "productName", target = "product.name")
	@Mapping(source = "productImage", target = "product.image")
	@Mapping(source = "productPrice", target = "product.price")
	public ShoppingProduct toShoppingProduct(ShoppingProductDTO shoppingProductDTO);

	public List<ShoppingProductDTO> toListShoppingProductDTO(List<ShoppingProduct> shoppingProducts);

	public List<ShoppingProduct> toListShoppingProduct(List<ShoppingProductDTO> shoppingProductDTOs);
}
