package online.store.controllers;

import java.util.List;
import java.util.stream.Collectors;

import online.store.services.ProductsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import online.store.dtos.ProductCategoryDTO;
import online.store.dtos.ProductResponseDTO;
import online.store.model.Product;
import online.store.model.ProductCategory;

@RestController
public class HomepageController {

	@Autowired
	private ProductsService productsService;

    @Autowired
    private ModelMapper modelMapper;
	
	@GetMapping("/categories")
	public String getAllProductCategories() {
		List<ProductCategory> categoryList = productsService.findAllProductCategories();
		List<String> categoriesDTO = categoryList.stream().map(this::convertToDto).collect(Collectors.toList());
				
		return String.join(",", categoriesDTO);
	}
	
	@GetMapping("deals_of_the_day/{numberOfProducts}")
	public Object getDealsOfTheDay(@PathVariable("numberOfProducts") Integer numberOfProducts) {
		List<Product> productList = productsService.findAtMostNumberOfProducts(numberOfProducts);
		ProductResponseDTO deals = new ProductResponseDTO();
		deals.setProducts(productList);
		
		return deals;
	}
	
	@GetMapping("/products")
	public Object getProductsByCategory(@RequestParam(required = false) String category) {
		List<Product> productList = null;
		if(category != null) {
			productList = productsService.findByCategory(category);
		} else {
			productList = productsService.findAllProducts();
		}
		
		ProductResponseDTO products = new ProductResponseDTO();
		products.setProducts(productList);
		
		return products;
	}
	
	
	
	private String convertToDto(ProductCategory productCategory) {
		ProductCategoryDTO productCategoryDTO = modelMapper.map(productCategory, ProductCategoryDTO.class);
	    
	    return productCategoryDTO.toString();
	}
}
