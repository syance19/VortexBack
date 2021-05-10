package com.vortexBackend.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexBackend.test.domain.Customer;
import com.vortexBackend.test.domain.Product;
import com.vortexBackend.test.domain.ShoppingCart;
import com.vortexBackend.test.domain.ShoppingProduct;
import com.vortexBackend.test.repository.ShoppingCartRepository;



@Service
@Scope("singleton")
public class CartServiceImpl implements CartService {
	@Autowired
	CustomerService customerService;

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	ProductService productService;

	@Autowired
	ShoppingProductService shoppingProductService;

	@Autowired
	PaymentMethodService paymentMethodService;

	@Override
	@Transactional(readOnly = false)
	public ShoppingCart createCart(String email) throws Exception {
		Customer customer = null;
		if (email == null || email.isBlank() == true) {
			throw new Exception("El email del cliente es nulo");
		}
		Optional<Customer> customerOptional = customerService.findById(email);
		if (customerOptional.isPresent() == false) {
			throw new Exception("No existe un customer con el email: " + email);
		}
		customer = customerOptional.get();
		if (customer.getEnable() == null || customer.getEnable().equals("N") == true) {
			throw new Exception("El cliente con email: " + email + " no esta habilitado");
		}
		ShoppingCart shoppingCart = new ShoppingCart(0, customer, null, 0, 0L, "Y", null);
		shoppingCart = shoppingCartService.save(shoppingCart);
		return shoppingCart;
	}

	@Override
	@Transactional(readOnly = false)
	public ShoppingProduct addProduct(Integer carId, String proId, Integer quantity) throws Exception {

		ShoppingCart shoppingCart = null;
		Product product = null;
		ShoppingProduct shoppingProduct = null;
		Long totalShoppingProduct = 0L;
		Long totalShoppingCart = 0L;
		Integer itemsShoppingCart = 0;
		Integer cantidadShpr = 0;

		if (carId == null || carId <= 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}

		if (proId == null || proId.isBlank() == true) {
			throw new Exception("El proId es nulo o menor a esta en blanco");
		}

		if (quantity == null || quantity <= 0) {
			throw new Exception("El quantity es nulo o menor a cero");
		}

		if (shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("El shoppingCart con carId no existe");
		}

		shoppingCart = shoppingCartService.findById(carId).get();

		if (shoppingCart.getEnable().equals("N") == true) {
			throw new Exception("El shoppingCart esta inhabilitado");
		}

		if (productService.findById(proId).isPresent() == false) {
			throw new Exception("El product no existe");
		}

		product = productService.findById(proId).get();

		if (product.getEnable().equals("N") == true) {
			throw new Exception("El product esta inhabilitado");
		}

		shoppingProduct = shoppingProductService.getShprByCarPro(carId, proId);

		if (shoppingProduct == null) {
			shoppingProduct = new ShoppingProduct();
			shoppingProduct.setProduct(product);
			shoppingProduct.setQuantity(quantity);
			shoppingProduct.setShoppingCart(shoppingCart);
			shoppingProduct.setShprId(0);
			totalShoppingProduct = Long.valueOf(product.getPrice() * quantity);
			shoppingProduct.setTotal(totalShoppingProduct);
			shoppingProduct = shoppingProductService.save(shoppingProduct);
		} else {
			cantidadShpr = shoppingProduct.getQuantity() + quantity;
			totalShoppingProduct = Long.valueOf(product.getPrice() * cantidadShpr);
			shoppingProduct.setQuantity(cantidadShpr);
			shoppingProduct.setTotal(totalShoppingProduct);
			shoppingProduct = shoppingProductService.update(shoppingProduct);
		}

		totalShoppingCart = shoppingProductService.totalShoppingProductByShoppingCart(carId);
		itemsShoppingCart = shoppingProductService.totalItems(carId);
		shoppingCart.setTotal(totalShoppingCart);
		shoppingCart.setItems(itemsShoppingCart);
		shoppingCartService.update(shoppingCart);

		return shoppingProduct;
	}

	@Override
	public void removeProduct(Integer carId, String proId) throws Exception {

	}

	@Override
	public void clearCart(Integer carId) throws Exception {

	}

	@Override
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId) throws Exception {

		return null;
	}

	@Override
	public List<ShoppingCart> findShoppingCartByEmail(String email) throws Exception {

		return null;
	}

	@Override
	public ShoppingCart finalizarCompra(Integer carId, Integer payId) throws Exception {

		return null;
	}

	@Override
	public List<ShoppingCart> findShcaByPayIdNull(String email) throws Exception {
		Customer customer = null;

		if (email == null || email.isBlank() == true) {
			throw new Exception("El email del cliente es nulo");
		}

		Optional<Customer> customerOptional = customerService.findById(email);
		if (customerOptional.isPresent() == false) {
			throw new Exception("No existe un customer con el email: " + email);
		}

		customer = customerOptional.get();

		if (customer.getEnable() == null || customer.getEnable().equals("N") == true) {
			throw new Exception("El cliente con email: " + email + " no esta habilitado");
		}
		return shoppingCartRepository.findShcaByPayIdNull(email);
	}

}
