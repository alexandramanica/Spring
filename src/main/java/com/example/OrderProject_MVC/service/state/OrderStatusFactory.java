package com.example.OrderProject_MVC.service.state;

import com.example.OrderProject_MVC.model.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderStatusFactory {
    private final Map<OrderStatus, IOrder> statesHashMap = new HashMap<>();

    public OrderStatusFactory(List<IOrder> statesList) {
        for(IOrder state: statesList){
            statesHashMap.put(state.sayStatus(), state);
        }
    }

    public IOrder getState(OrderStatus orderStatus){
        IOrder state = statesHashMap.get(orderStatus);
        if (state == null) throw new IllegalStateException("This status doesn't exist");
        return state;
    }
}
