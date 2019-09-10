package ro.itschool.tema25.controller;

import org.springframework.web.bind.annotation.*;
import ro.itschool.tema25.model.Order;
import ro.itschool.tema25.model.Status;
import ro.itschool.tema25.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    List<Order> getOrders(@RequestParam(required = false) Status status) {
        return orderService.getOrders(status);
    }

    @PostMapping
    void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @PatchMapping("{orderId}")
    void movingTheStatus(Integer orderId) {
        orderService.movingTheStatus(orderId);
    }

    @DeleteMapping("{orderId}")
    void deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
    }
}
