package jpa_task.impl.domain;

import jpa_task.impl.domain.services.MyService;
import jpa_task.impl.domain.services.OrderService;
import jpa_task.impl.domain.services.SupplierService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ServiceConfig {
    @Bean
    public MyService myService(OrderService orderService, SupplierService supplierService){
        return new MyService(orderService, supplierService);
    }
}
