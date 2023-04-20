package online.store.dtos;

import java.util.List;

import online.store.model.Product;

public class ProductResponseDTO {
	List<Product> products;

	
	public ProductResponseDTO() {
		super();
	}
	
	public ProductResponseDTO(List<Product> products) {
		super();
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
