package ro.itschool.tema25.service;

import org.springframework.stereotype.Service;
import ro.itschool.tema25.model.Category;
import ro.itschool.tema25.model.Product;
import ro.itschool.tema25.model.exceptions.PositiveStockException;
import ro.itschool.tema25.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(Category category) {
        if (category == null) {
            return getProducts();
        }
        return productRepository.findAllByCategory(category);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        Product existingProduct = productRepository.findByName(product.getName());
        if (existingProduct != null) {
            product.setProductId(existingProduct.getProductId());
            product.setStock(product.getStock() + existingProduct.getStock());
        }
        return productRepository.save(product);
    }

    public void updateProduct(Integer productId, Product product) {
        Product existingProduct = productRepository.findByName(product.getName());
        if (existingProduct != null) {
            product.setStock(product.getStock() + existingProduct.getStock());
            product.setProductId(existingProduct.getProductId());
        } else {
            product.setProductId(productId);
        }
        productRepository.save(product);
    }


    public void deleteProduct(Integer productId) {
        if (productRepository.findById(productId).get().getStock() > 0) {
            throw new PositiveStockException("You cannot delete a product in stock");
        }
        productRepository.deleteById(productId);
    }
}
