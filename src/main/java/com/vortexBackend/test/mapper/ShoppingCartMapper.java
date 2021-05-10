package com.vortexBackend.test.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vortexBackend.test.domain.ShoppingCart;
import com.vortexBackend.test.dto.ShoppingCartDTO;

@Mapper
public interface ShoppingCartMapper {
	@Mapping(source = "customer.email", target = "customerEmail")
	@Mapping(source = "paymentMethod.payId", target = "paymentMethodId")
	public ShoppingCartDTO toShoppingCartDTO(ShoppingCart shoppingCart);

	@Mapping(source = "customerEmail", target = "customer.email")
	@Mapping(source = "paymentMethodId", target = "paymentMethod.payId")
	public ShoppingCart toShoppingCart(ShoppingCartDTO shoppingCartDTO);

	public List<ShoppingCartDTO> toListShoppingCartDTO(List<ShoppingCart> shoppingCarts);

	public List<ShoppingCart> toListShoppingCart(List<ShoppingCartDTO> shoppingCartDTOs);

}
