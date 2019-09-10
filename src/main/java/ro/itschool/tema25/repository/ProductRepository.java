package ro.itschool.tema25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.tema25.model.Category;
import ro.itschool.tema25.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategory(Category category);
    Product findByName(String name);
}
