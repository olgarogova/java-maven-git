package jpa_task.impl.domain;

import jpa_task.impl.domain.services.MyService;
import jpa_task.impl.domain.services.OrderService;
import jpa_task.impl.domain.services.SupplierService;

//@Configuration
//@ComponentScan
public class ServiceConfig {
    //@Bean
    public MyService myService(OrderService orderService, SupplierService supplierService){
        return new MyService(orderService, supplierService);
    }
}
