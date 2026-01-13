package com.example.OrderProject_MVC.controller.ThymeLeafPresenter;

import com.example.OrderProject_MVC.dto.OrderResponseDTO;
import com.example.OrderProject_MVC.model.OrderStatus;
import com.example.OrderProject_MVC.service.OrderService;
import com.example.OrderProject_MVC.service.strategy.Filter;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

//Presenterul doar prezinta nu si modifica, se muta doar get-urile din controller fara post uri
@Component
public class AdminPresenter implements IPresenter {
    private final OrderService orderService;

    public AdminPresenter(OrderService orderService) {
        this.orderService = orderService;
    }

    // LISTĂ + FILTRARE
    public void prepareOrdersPage(String orderStatus,
                                  String email,
                                  String date,
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
    }

    // DETALII COMANDĂ
    public void prepareOrderDetails(Long id, Model model) {
        OrderResponseDTO order = orderService.getOrderDetails(id);
        model.addAttribute("order", order);
        model.addAttribute("allStatuses", Arrays.asList(OrderStatus.values()));
    }


}
