package ru.tim_5.Services;

import ru.tim_5.Exceptions.ProductNotFoundException;
import ru.tim_5.repositories.ProductRepository;
import ru.tim_5.models.Product;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void addProduct(Product product) {
        productRepository.saveFile(product);
    }
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public Product getProductId(int id) throws ProductNotFoundException {
        return productRepository.findById(id);
    }
}
