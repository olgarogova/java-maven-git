package jpa_task.impl.domain;

import jpa_task.impl.domain.services.MyService;
import jpa_task.impl.domain.services.OrderService;
import jpa_task.impl.domain.services.SupplierService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public MyService myService(OrderService orderService, SupplierService supplierService){
        return new MyService(orderService, supplierService);
    }

    @Bean
    public OrderService orderService(){
        return new OrderService();
    }

    @Bean
    public SupplierService supplierService() {
        return new SupplierService();
    }
}
