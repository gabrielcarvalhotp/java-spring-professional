package com.agvsistemas.componentesinjecaodependencia;

import com.agvsistemas.componentesinjecaodependencia.domains.Order;
import com.agvsistemas.componentesinjecaodependencia.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DecimalFormat;

@SpringBootApplication
public class ComponentesinjecaodependenciaApplication implements CommandLineRunner {

    @Autowired
    OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(ComponentesinjecaodependenciaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        DecimalFormat df1 = new DecimalFormat("R$ #,##0.00");

        Order order1 = new Order(1034, 150.00, 20.00);
        System.out.println("Pedido código: " + order1.getCode());
        System.out.println("Valor total: " + df1.format(orderService.total(order1)));

        System.out.println();

        Order order2 = new Order(2282, 800.00, 10.00);
        System.out.println("Pedido código: " + order2.getCode());
        System.out.println("Valor total: " + df1.format(orderService.total(order2)));

        System.out.println();

        Order order3 = new Order(1309, 95.90, 0.00);
        System.out.println("Pedido código: " + order3.getCode());
        System.out.println("Valor total: " + df1.format(orderService.total(order3)));
    }
}
