package online.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import online.store.dtos.OrderRequestDTO;
import online.store.exceptions.CreditCardValidationException;
import online.store.exceptions.OrderValidationException;
import online.store.services.CreditCardValidationService;
import online.store.services.OrdersService;

@RestController
public class CheckoutController {

	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private CreditCardValidationService creditCardValidationService;
	
	@PostMapping("/checkout")
	public Object createOrder(@RequestBody OrderRequestDTO order) {
		try {
			creditCardValidationService.validate(order.getCreditCard());
			
			ordersService.saveOrder(order);
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch(OrderValidationException orderEx) {
			return new ResponseEntity<>(orderEx.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}catch(CreditCardValidationException cardEx) {
			throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, cardEx.getLocalizedMessage());
		}
	}
	
	@ExceptionHandler({CreditCardValidationException.class})
	public ResponseEntity<String> handleCreditCardError(Exception ex) {
	    return new ResponseEntity<>("Credit card is invalid, please use another form of payment",
	            HttpStatus.BAD_REQUEST);
	}
}
