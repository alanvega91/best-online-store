package online.store.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import online.store.dtos.OrderRequestDTO;
import online.store.exceptions.CreditCardValidationException;
import online.store.exceptions.OrderValidationException;
import online.store.model.Order;
import online.store.model.Product;
import online.store.repositories.OrderRepository;
import online.store.repositories.ProductRepository;

@Service
public class OrdersService {
	
	private long maxNumberOfItems = 0;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public OrdersService(OrderRepository orderRepository,
            @Value("${products.service.max-number-of-items:25}") long maxNumberOfItems) {
		this.orderRepository = orderRepository;
		this.maxNumberOfItems = maxNumberOfItems;
	}
	
	
	public Object saveOrder(OrderRequestDTO order) throws OrderValidationException, CreditCardValidationException {
		if(order.getFirstName().isBlank()) {
			throw new OrderValidationException("First name is missing");
		}
		if(order.getLastName().isBlank()) {
			throw new OrderValidationException("Last name is missing");
		}
		if(order.getCreditCard().isBlank()) {
			throw new CreditCardValidationException("Credit card information is missing");
		}
		List<Order> ordersToSave = new ArrayList<Order>();
		for(Map<String, String> product : order.getProducts()) {
			Order orderToSave = new Order();
			orderToSave.setCreditCard(order.getCreditCard());
			orderToSave.setEmail(order.getEmail());
			orderToSave.setFirstName(order.getFirstName());
			orderToSave.setLastName(order.getLastName());
			orderToSave.setShippingAddress(order.getShippingAddress());
			orderToSave.setQuantity(Long.valueOf(product.get("quantity")));
			Product productSaved = productRepository.getById(Long.valueOf(product.get("productId")));
			orderToSave.setProduct(productSaved);
			ordersToSave.add(orderToSave);
		}
		
		validateNumberOfItemsOrdered(ordersToSave);
		return orderRepository.saveAll(ordersToSave);
	}
	
	private void validateNumberOfItemsOrdered(Iterable<Order> orders) {
	    long totalNumberOfItems = 0;
	    for (Order order: orders)  {
	        totalNumberOfItems += order.getQuantity();
	    }
	    if (totalNumberOfItems > maxNumberOfItems) {
	        throw new IllegalStateException(String.format("Number of products %d exceeded the limit of %d",
	                totalNumberOfItems, maxNumberOfItems));
	    }
	}
}
