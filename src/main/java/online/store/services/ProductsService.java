package online.store.services;

import online.store.model.Product;
import online.store.model.ProductCategory;
import online.store.repositories.ProductCategoryRepository;
import online.store.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michael Pogrebinsky - www.topdeveloperacademy.com
 * Integrates with the database API and handles products and categories business logic
 */
@Service
public class ProductsService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductsService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> findAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    public List<Product> findAtMostNumberOfProducts(Integer numberOfProducts) {
        return productRepository.findAtMostNumberOfProducts(numberOfProducts);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}
