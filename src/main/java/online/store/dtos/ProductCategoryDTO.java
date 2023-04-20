package online.store.dtos;

import com.fasterxml.jackson.annotation.JsonValue;

public class ProductCategoryDTO {
	
	@JsonValue
	private String category;

	public ProductCategoryDTO(String category) {
		super();
		this.category = category;
	}
	
	public ProductCategoryDTO() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return category;
	}
}
