package com.example.OrderProject_MVC.service.specification;

import com.example.OrderProject_MVC.model.Client;
import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.repository.ClientRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ClientSpecification implements IOrderSpecification{
    private final ClientRepository clientRepository;

    public ClientSpecification(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Specification<Order> getOrdersSpecification(String filterSpecification) {
        Client client = clientRepository.findByEmail(filterSpecification)
                .orElseThrow(() -> new IllegalArgumentException("The email adress does not exist."));
        return (root, query, cb) -> cb.equal(root.get("client"), client);
    }

    @Override
    public FilterSpecification getFilterSpecification() {
        return FilterSpecification.CLIENT;
    }
}
