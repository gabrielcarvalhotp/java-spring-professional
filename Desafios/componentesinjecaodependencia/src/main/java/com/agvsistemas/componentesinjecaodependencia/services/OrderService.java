package com.agvsistemas.componentesinjecaodependencia.services;

import com.agvsistemas.componentesinjecaodependencia.domains.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    ShippingService shippingService;

    public Double total(Order order) {
        Double shippingValue = shippingService.shipment(order);
        return order.getValueWithDiscount() + shippingValue;
    }
}
