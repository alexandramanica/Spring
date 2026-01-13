package com.example.OrderProject_MVC.service.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FilterFactory {
    private Map<Filter, IFilterStrategy> filterStrategyMap = new HashMap<>();

    public FilterFactory(List<IFilterStrategy> strategies){
        for(IFilterStrategy strategy : strategies){
            filterStrategyMap.put(strategy.getFilter(), strategy);
        }
    }

    public IFilterStrategy getStrategy(Filter filter){
        IFilterStrategy strategy = filterStrategyMap.get(filter);
        if(strategy == null){
            throw new IllegalArgumentException("The filter does not exist. Strategy doesn't match.");
        }
        return strategy;
    }

//    public FilterFactory(List<IFilterStrategy> strategyList) {
//        for (IFilterStrategy s : strategyList) {
//            Filter key = s.getFilter();
//            if (strategies.containsKey(key)) {
//                throw new IllegalStateException("Duplicate strategy for filter: " + key);
//            }
//            strategies.put(key, s);
//        }
//    }
}
