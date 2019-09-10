package ro.itschool.tema25.controller;

import org.springframework.web.bind.annotation.*;
import ro.itschool.tema25.model.Category;
import ro.itschool.tema25.model.Product;
import ro.itschool.tema25.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    List<Product> getProducts(@RequestParam(required = false) Category category) {
        return productService.getProducts(category);
    }

    @PostMapping
    Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("{productId}")
    void updateProduct(@PathVariable Integer productId, @RequestBody Product product){
        productService.updateProduct(productId,product);
    }

    @DeleteMapping("{productId}")
    void deleteProduct(@PathVariable Integer productId){
        productService.deleteProduct(productId);
    }

}
