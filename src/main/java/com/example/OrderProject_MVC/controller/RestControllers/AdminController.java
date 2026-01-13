package com.example.OrderProject_MVC.controller.RestControllers;

import com.example.OrderProject_MVC.dto.OrderRequestDTO;
import com.example.OrderProject_MVC.dto.OrderResponseDTO;
import com.example.OrderProject_MVC.model.OrderStatus;
import com.example.OrderProject_MVC.service.OrderService;
import com.example.OrderProject_MVC.service.strategy.Filter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all-orders")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable(required = true) Long id){
        OrderResponseDTO orderDTO = orderService.getOrderDetails(id);
        return ResponseEntity.ok(orderDTO);
    }
    @DeleteMapping("/cancel-order/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable(required = true) Long id){
        orderService.cancelOrder(id);
        return ResponseEntity.ok("The order was cancelled.");
    }

    @PostMapping("/update-order/{id}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable(required = true) Long id,
                                                    @RequestParam(required = true) String orderStatus){
        orderService.updateOrder(id, OrderStatus.valueOf(orderStatus.toUpperCase()));
        return ResponseEntity.ok("The order was updated with status : " + orderStatus);
    }

    @PostMapping("/update-order-way-two/{id}")
    public ResponseEntity<String> updateOrderStatusWayTwo(@PathVariable(required = true) Long id,
                                                    @RequestBody(required = true) OrderRequestDTO orderRequestDTO){
        orderService.updateOrderWayTwo(id, orderRequestDTO);
        return ResponseEntity.ok("The order was updated with status : " + orderRequestDTO.getOrderStatus());
    }

    @GetMapping("/filter-orders")
    public ResponseEntity<List<OrderResponseDTO>> filterOrders(@RequestParam(required = false) String orderStatus,
                                                              @RequestParam(required = false) String email,
                                                              @RequestParam(required = false) String date){
        if ((email ==null|| email.isBlank()) && ( orderStatus ==null || orderStatus.isBlank())){
            List<OrderResponseDTO> orders = orderService.filterStrategy(Filter.DATE, date);
            return ResponseEntity.ok(orders);
        } else if ((email ==null|| email.isBlank()) && (date ==null|| date.isBlank())) {
            List<OrderResponseDTO> orders = orderService.filterStrategy(Filter.STATUS, orderStatus);
            return ResponseEntity.ok(orders);
        }else if ((date ==null|| date.isBlank()) && (orderStatus ==null || orderStatus.isBlank())){
            List<OrderResponseDTO> orders = orderService.filterStrategy(Filter.CLIENT, email);
            return ResponseEntity.ok(orders);
        } else {
            System.out.println("Multiple or no parameters in place. Use Specification");
            return ResponseEntity.noContent().build();
        }
    }
}
