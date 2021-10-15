package jpa_task.impl.domain.services;

import jpa_task.impl.domain.entity.Customer;
import jpa_task.impl.domain.entity.Order;
import jpa_task.impl.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MyService {

    private final OrderService orderService;
    private final SupplierService supplierService;

    @Autowired
    public MyService(OrderService orderService, SupplierService supplierService) {
        this.orderService = orderService;
        this.supplierService = supplierService;
    }

    public void fulfillOrder(Order order, Product product, Set<Product> products, Customer customer) {
        orderService.createNewOrder(order);
        orderService.addProductToOrder(product, order);
        orderService.buyProduct(customer, product);
        supplierService.insertProducts(products);
    }
}
