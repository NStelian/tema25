package ro.itschool.tema25;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.itschool.tema25.model.Category;
import ro.itschool.tema25.model.Order;
import ro.itschool.tema25.model.Product;
import ro.itschool.tema25.repository.OrderRepository;
import ro.itschool.tema25.repository.ProductRepository;

import static ro.itschool.tema25.model.Category.*;

@SpringBootApplication
public class Tema25Application {

    public static void main(String[] args) {
        SpringApplication.run(Tema25Application.class, args);
    }

    @Bean
    CommandLineRunner atStartup(ProductRepository productRepository, OrderRepository orderRepository) {
        return args -> {
            productRepository.save(new Product("paine", FOOD, 2.0, 2));
            productRepository.save(new Product("laptop", ELECTRONICS, 199.99, 20));
            productRepository.save(new Product("table", FURNITURE, 19.99, 10));
        };
    }

}
