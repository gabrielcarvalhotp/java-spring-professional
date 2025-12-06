package com.agvsistemas.componentesinjecaodependencia.services;

import com.agvsistemas.componentesinjecaodependencia.domains.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public Double shipment(Order order) {
        if (order.getValueWithDiscount() > 200.00) {
            return 0.0;
        }

        if (order.getValueWithDiscount() < 100.00) {
            return 20.00;
        } else {
            return 12.00;
        }
    }
}
