package online.store.dtos;

import java.util.List;
import java.util.Map;

public class OrderRequestDTO {
	private String firstName;
	private String lastName;
	private String email;
	private String shippingAddress;
	private String creditCard;
	private List<Map<String, String>> products;
	
	
	public OrderRequestDTO() {
		super();
	}
	
	public OrderRequestDTO(String firstName, String lastName, String email, String shippingAddress, String creditCard,
			List<Map<String, String>> products) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.shippingAddress = shippingAddress;
		this.creditCard = creditCard;
		this.products = products;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public List<Map<String, String>> getProducts() {
		return products;
	}

	public void setProducts(List<Map<String, String>> products) {
		this.products = products;
	}
}
