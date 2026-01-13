package com.example.OrderProject_MVC.service.specification;

import com.example.OrderProject_MVC.service.strategy.IFilterStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FilterSpecificationFactory {
    private Map<FilterSpecification, IOrderSpecification> filterSpecificationMap = new HashMap<>();

    public FilterSpecificationFactory(List<IOrderSpecification> orderSpecificationList){
        for(IOrderSpecification orderSpecification: orderSpecificationList){
            filterSpecificationMap.put(orderSpecification.getFilterSpecification(), orderSpecification);
        }
    }

    public IOrderSpecification getSpecification(FilterSpecification filterSpecification){
        IOrderSpecification specification = filterSpecificationMap.get(filterSpecification);
        if (specification == null) throw new IllegalArgumentException("Strategia nu exista.");
        return specification;
    }
}
