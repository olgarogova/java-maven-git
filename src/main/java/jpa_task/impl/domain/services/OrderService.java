package jpa_task.impl.domain.services;

import jpa_task.impl.domain.entity.Customer;
import jpa_task.impl.domain.entity.Order;
import jpa_task.impl.domain.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void createNewOrder(Order order){
        System.out.println("Order created, " + order.toString());
    }

    public void addProductToOrder(Product product, Order order) {
        System.out.println("Product " + product + " added to order " + order);
    }

    public void buyProduct(Customer customer, Product product){
        System.out.println("Customer " + customer + " bought product " + product);
    }
}
