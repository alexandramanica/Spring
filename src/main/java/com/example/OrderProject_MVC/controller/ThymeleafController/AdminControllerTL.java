package com.example.OrderProject_MVC.controller.ThymeleafController;

import com.example.OrderProject_MVC.dto.OrderRequestDTO;
import com.example.OrderProject_MVC.dto.OrderResponseDTO;
import com.example.OrderProject_MVC.model.OrderStatus;
import com.example.OrderProject_MVC.service.OrderService;
import com.example.OrderProject_MVC.service.strategy.Filter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControllerTL {

    private final OrderService orderService;

    public AdminControllerTL(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String ordersPage(@RequestParam(required = false) String orderStatus,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String date,
                             Model model) {

        boolean hasStatus = orderStatus != null && !orderStatus.isBlank();
        boolean hasEmail  = email != null && !email.isBlank();
        boolean hasDate   = date != null && !date.isBlank();

        List<OrderResponseDTO> orders;

        if (!hasStatus && !hasEmail && !hasDate) {
            orders = orderService.getAllOrders();
        } else if (hasDate && !hasStatus && !hasEmail) {
            orders = orderService.filterStrategy(Filter.DATE, date);
        } else if (hasStatus && !hasEmail && !hasDate) {
            orders = orderService.filterStrategy(Filter.STATUS, orderStatus);
        } else if (hasEmail && !hasStatus && !hasDate) {
            orders = orderService.filterStrategy(Filter.CLIENT, email);
        } else {
            orders = orderService.getAllOrders();
            model.addAttribute("error", "Folosește un singur filtru.");
        }

        model.addAttribute("orders", orders);
        model.addAttribute("allStatuses", Arrays.asList(OrderStatus.values()));

        model.addAttribute("filterStatus", orderStatus);
        model.addAttribute("filterEmail", email);
        model.addAttribute("filterDate", date);

        return "admin/orders";
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable(required = true) Long id,  Model model){
        OrderResponseDTO orderDTO = orderService.getOrderDetails(id);
        model.addAttribute("order", orderDTO);
        model.addAttribute("allStatuses", Arrays.asList(OrderStatus.values()));
        return "admin/order-details";
    }
    @PostMapping("/orders/{id}/status")
    public String updateOrderStatus(@PathVariable Long id,
                                    @RequestParam String orderStatus,
                                    RedirectAttributes ra) {
        orderService.updateOrder(id, OrderStatus.valueOf(orderStatus.toUpperCase()));
        ra.addFlashAttribute("msg", "Status actualizat: " + orderStatus);
        return "redirect:/admin/order/" + id;
    }

    // 4) ANULARE (din buton) - se mapeaza ca post chiar daca e delete - thymeleaf rule
    @PostMapping("/orders/{id}/cancel")
    public String cancelOrder(@PathVariable Long id, RedirectAttributes ra) {
        orderService.cancelOrder(id);
        ra.addFlashAttribute("msg", "Comanda a fost anulată.");
        return "redirect:/admin/orders";
    }
}
