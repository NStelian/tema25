package ro.itschool.tema25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.tema25.model.Order;
import ro.itschool.tema25.model.Status;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByStatus(Status status);
}
