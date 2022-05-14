package ua.aleksanid.maindomain.services;

import org.springframework.stereotype.Service;
import ua.aleksanid.maindomain.models.Product;
import ua.aleksanid.maindomain.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long productId) {
        return productRepository.getById(productId);
    }
}
