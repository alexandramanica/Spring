package com.example.OrderProject_MVC.service.strategy;

import com.example.OrderProject_MVC.model.Client;
import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.repository.ClientRepository;
import com.example.OrderProject_MVC.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientFilter implements IFilterStrategy {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    public ClientFilter(OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Order> filterOrders(String filterValue) {
        Client client = clientRepository.findByEmail(filterValue)
                .orElseThrow(() -> new IllegalArgumentException("Couldn't find a client with this email. Filter failed"));

        return orderRepository.findByClient(client);
    }

    @Override
    public Filter getFilter() {
        return Filter.CLIENT;
    }
}
