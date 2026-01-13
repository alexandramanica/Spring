package com.example.OrderProject_MVC.controller.ThymeleafController;

import com.example.OrderProject_MVC.dto.NotificationResponseDTO;
import com.example.OrderProject_MVC.dto.OrderRequestDTO;
import com.example.OrderProject_MVC.dto.OrderResponseDTO;
import com.example.OrderProject_MVC.model.OrderStatus;
import com.example.OrderProject_MVC.service.NotificationService;
import com.example.OrderProject_MVC.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/client")
public class ClientController {

    private final OrderService orderService;
    private final NotificationService notificationServicel;

    public ClientController(OrderService orderService, NotificationService notificationServicel) {
        this.orderService = orderService;
        this.notificationServicel = notificationServicel;
    }

    @GetMapping("/orders")
    public String getClientOrders(@RequestParam(required = false) String email, Model model){
        List<OrderResponseDTO> orderResponseDTOS;
    if (email != null && !email.isBlank()){
        orderResponseDTOS = orderService.getAllOrdersByEmail(email);
    } else{
        orderResponseDTOS = new ArrayList<>();
    }
        model.addAttribute("orders", orderResponseDTOS);
        model.addAttribute("filterEmail", email);
        return "client/orders";
    }

    @GetMapping("/notifications/{orderId}")
    public String getClientNotification(@PathVariable Long orderId, Model model){
        List<NotificationResponseDTO> notificationResponseDTOS;
        if (orderId != null){
            notificationResponseDTOS = notificationServicel.findNotifications(orderId);
        } else{
            notificationResponseDTOS = new ArrayList<>();
        }
        model.addAttribute("notifications", notificationResponseDTOS);
        model.addAttribute("orderId", orderId);
        return "client/notifications";
    }

    //PT ADAUGARE DE NOI COMENZI E NEVOIE DE 2 CERERI
    @GetMapping("/add-orders")
    public String showAddOrderForm(Model model) {
        model.addAttribute("order", new OrderRequestDTO());     // obiectul pe care se leagă formularul
        model.addAttribute("allStatuses", OrderStatus.values()); // pentru <select>
        return "client/add-orders"; // templates/client/add-orders.html
    }

    @PostMapping("/add-orders")
    public String addOrder(@ModelAttribute("order") OrderRequestDTO orderRequestDTO,
                           RedirectAttributes ra) {

        orderService.saveOrder(orderRequestDTO);
        ra.addFlashAttribute("msg", "Comanda a fost adăugată cu succes");

        return "redirect:/client/add-orders";
    }

    //EDIT
    @GetMapping("/orders/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        OrderResponseDTO order = orderService.getOrderDetails(id);

        OrderRequestDTO form = new OrderRequestDTO(
                order.getOrderDate(),
                order.getPrice(),
                order.getOrderStatus(),
                order.getEmail()
        );

        model.addAttribute("orderId", id);
        model.addAttribute("order", form);
        model.addAttribute("allStatuses", OrderStatus.values());

        return "client/edit-orders";
    }

    @PostMapping("/orders/{id}/edit")
    public String updateOrder(@PathVariable Long id,
                              @ModelAttribute("order") OrderRequestDTO orderRequestDTO,
                              RedirectAttributes ra) {

        orderService.updateFullOrder(id, orderRequestDTO); // sau metoda ta de update
        ra.addFlashAttribute("msg", "Comanda a fost actualizată.");

        return "redirect:/client/orders";
    }



}
