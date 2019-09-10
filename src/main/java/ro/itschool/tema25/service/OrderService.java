package ro.itschool.tema25.service;

import org.springframework.stereotype.Service;
import ro.itschool.tema25.model.Order;
import ro.itschool.tema25.model.Status;
import ro.itschool.tema25.model.exceptions.IncorrectOrderFormatException;
import ro.itschool.tema25.repository.OrderRepository;
import ro.itschool.tema25.repository.ProductRepository;

import java.util.List;

import static ro.itschool.tema25.model.Status.*;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getOrders(Status status) {
        if (status == null) {
            return getOrders();
        }
        return orderRepository.findAllByStatus(status);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(Order order) {
        orderCheck(order);
        order.setPrice(productRepository.findById(order.getProductId()).get().getPrice() * order.getQuantity());
        order.setStatus(PENDING);
        orderRepository.save(order);
    }

    public void movingTheStatus(Integer orderId) {
        Order order = orderRepository.findById(orderId).get();
        if (order.getStatus().equals(PENDING)) {
            order.setStatus(ACCEPTED);
        }
        orderRepository.save(order);
    }

    public void deleteOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(CANCELED);
        orderRepository.save(order);
    }

    private void orderCheck(Order order) {
        if (order.getQuantity() < 0) {
            throw new IncorrectOrderFormatException("The quantity should be greater than 0.");
        } else if (order.getPrice() < 0) {
            throw new IncorrectOrderFormatException("The price should be greater than 0.");
        }
    }
}
