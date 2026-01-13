package com.example.OrderProject_MVC.service;

import com.example.OrderProject_MVC.dto.OrderRequestDTO;
import com.example.OrderProject_MVC.dto.OrderResponseDTO;
import com.example.OrderProject_MVC.mapper.OrderMapper;
import com.example.OrderProject_MVC.model.Client;
import com.example.OrderProject_MVC.model.Order;
import com.example.OrderProject_MVC.model.OrderStatus;
import com.example.OrderProject_MVC.repository.ClientRepository;
import com.example.OrderProject_MVC.repository.OrderRepository;
import com.example.OrderProject_MVC.service.observer.EventOrderChangedStasus;
import com.example.OrderProject_MVC.service.specification.FilterSpecification;
import com.example.OrderProject_MVC.service.specification.FilterSpecificationFactory;
import com.example.OrderProject_MVC.service.specification.IOrderSpecification;
import com.example.OrderProject_MVC.service.state.IOrder;
import com.example.OrderProject_MVC.service.state.OrderStatusFactory;
import com.example.OrderProject_MVC.service.strategy.Filter;
import com.example.OrderProject_MVC.service.strategy.FilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final FilterFactory filterFactory;
    private final FilterSpecificationFactory filterSpecificationFactory;
    private final OrderStatusFactory stateFactory;
    private final ApplicationEventPublisher eventPublisher;


    @Autowired
    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository, FilterFactory filterFactory, FilterSpecificationFactory filterSpecificationFactory, OrderStatusFactory stateFactory, ApplicationEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.filterFactory = filterFactory;
        this.filterSpecificationFactory = filterSpecificationFactory;
        this.stateFactory = stateFactory;
        this.eventPublisher = eventPublisher;
    }

    public OrderResponseDTO getOrderDetails(Long id){
        OrderResponseDTO order = orderRepository.findById(id).map(OrderMapper::orderToResponseDTO).orElseThrow(() -> new RuntimeException("The order does not exist."));
       return order;
    }

    public List<OrderResponseDTO> getAllOrders(){
        return orderRepository.findAll().stream().map(OrderMapper::orderToResponseDTO).toList();
    }

    public List<OrderResponseDTO> getAllOrdersByEmail(String email){
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Client doesn't exist."));
        return orderRepository.findByClient(client).stream().map(OrderMapper::orderToResponseDTO).toList();
    }

    public List<OrderResponseDTO> filterStrategy(Filter filter, String value){
        return filterFactory.getStrategy(filter).filterOrders(value).stream().map(OrderMapper::orderToResponseDTO).toList();
    }

    public List<OrderResponseDTO> filterSpecification(Map<FilterSpecification, String > specificationMap){
        Specification<Order> specification = (root, query, cb) -> cb.conjunction();

        for(Map.Entry<FilterSpecification, String> entry : specificationMap.entrySet()){
            IOrderSpecification orderSpecification = filterSpecificationFactory.getSpecification(entry.getKey());
            Specification<Order> currentSpecification = orderSpecification.getOrdersSpecification(entry.getValue());
            specification = specification.and(currentSpecification); //merge si cu or

        }
        // se implementeaza in order obligatoriu si JpaSpecificationExecutor<Order>
        return orderRepository.findAll(specification)
                .stream()
                .map(OrderMapper::orderToResponseDTO)
                .toList();
    }

    public void updateOrder(Long id, OrderStatus orderStatus){
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Couldn't find order"));

        IOrder currentState = stateFactory.getState(order.getOrderStatus());

        switch(orderStatus){
            case PROCESATA -> currentState.processOrder(order);
            case EXPEDIATA -> currentState.shipOrder(order);
            case LIVRATA -> currentState.deliverOrder(order);
            default -> throw new IllegalStateException("This state isn't possible");
        }
        orderRepository.save(order);

        if (orderStatus == order.getOrderStatus()){
            eventPublisher.publishEvent(new EventOrderChangedStasus(order.getId(), orderStatus, order.getOrderStatus()));
        }
    }

    public void updateOrderWayTwo(Long id, OrderRequestDTO orderRequestDTO){
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Couldn't find order"));

        OrderStatus orderStatus = orderRequestDTO.getOrderStatus();

        IOrder currentState = stateFactory.getState(order.getOrderStatus());

        switch(orderStatus){
            case PROCESATA -> currentState.processOrder(order);
            case EXPEDIATA -> currentState.shipOrder(order);
            case LIVRATA -> currentState.deliverOrder(order);
            default -> throw new IllegalStateException("This state isn't possible");
        }
        orderRepository.save(order);

        if (orderStatus == order.getOrderStatus()){
            eventPublisher.publishEvent(new EventOrderChangedStasus(order.getId(), orderStatus, order.getOrderStatus()));
        }
    }


    public Order updateFullOrder(Long id, OrderRequestDTO dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // update câmpuri editabile
        order.setOrderDate(dto.getOrderDate());
        order.setPrice(dto.getPrice());
        order.setOrderStatus(dto.getOrderStatus());

        // NU modifici clientul aici (email rămâne al comenzii)
        return orderRepository.save(order);
    }


    public void cancelOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Couldn't find order"));
        stateFactory.getState(order.getOrderStatus()).cancelOrder(order);
        orderRepository.delete(order);
    }

    public Order saveOrder(OrderRequestDTO orderRequestDTO){
        Client client = clientRepository.findByEmail(orderRequestDTO.getEmail()).orElseThrow(() -> new RuntimeException("Client not found"));
        return orderRepository.save(new Order(orderRequestDTO.getOrderDate(), orderRequestDTO.getPrice(),
                orderRequestDTO.getOrderStatus(), client));
    }

}
