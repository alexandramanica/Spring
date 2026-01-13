package com.example.OrderProject_MVC.controller.ThymeLeafPresenter;

import com.example.OrderProject_MVC.model.OrderStatus;
import com.example.OrderProject_MVC.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("presenter")
public class AdminTLController {

    private final OrderService orderService;
    private final AdminPresenter adminPresenter;

    public AdminTLController(OrderService orderService, AdminPresenter adminPresenter) {
        this.orderService = orderService;
        this.adminPresenter = adminPresenter;
    }

    @GetMapping("/orders")
    public String ordersPage(@RequestParam(required = false) String orderStatus,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String date,
                             Model model) {
        adminPresenter.prepareOrdersPage(orderStatus, email, date, model);
        return "presenter/orders";
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable(required = true) Long id, Model model){
        adminPresenter.prepareOrderDetails(id, model);
        return "presenter/order-details";
    }
    @PostMapping("/orders/{id}/status")
    public String updateOrderStatus(@PathVariable Long id,
                                    @RequestParam String orderStatus,
                                    RedirectAttributes ra) {
        orderService.updateOrder(id, OrderStatus.valueOf(orderStatus.toUpperCase()));
        ra.addFlashAttribute("msg", "Status actualizat: " + orderStatus);
        return "redirect:/presenter/order/" + id;
    }

    // 4) ANULARE (din buton) - se mapeaza ca post chiar daca e delete - thymeleaf rule
    @PostMapping("/orders/{id}/cancel")
    public String cancelOrder(@PathVariable Long id, RedirectAttributes ra) {
        orderService.cancelOrder(id);
        ra.addFlashAttribute("msg", "Comanda a fost anulată.");
        return "redirect:/presenter/orders";
    }
}
