package jpa_task.impl;

import jpa_task.impl.domain.JPAUtil;
import jpa_task.impl.domain.crud.CrudOperations;
import jpa_task.impl.domain.entity.Customer;
import jpa_task.impl.domain.entity.Order;
import jpa_task.impl.domain.entity.Product;
import jpa_task.impl.domain.entity.Supplier;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args)  {
        workWithHibernate();
    }

    public static void workWithHibernate() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        CrudOperations<Customer> crudOperationsCustomer = new CrudOperations<>(entityManager, Customer.class);
        CrudOperations<Supplier> crudOperationsSupplier = new CrudOperations<>(entityManager, Supplier.class);
        CrudOperations<Order> crudOperationsOrder = new CrudOperations<>(entityManager, Order.class);
        CrudOperations<Product> crudOperationsProduct = new CrudOperations<>(entityManager, Product.class);

        Date currentDate = new Date();

        Customer customer1 = new Customer("Customer1", "1-111-111-11-11");
        Customer customer2 = new Customer("Customer2", "2-222-222-22-22");
        Customer customer3 = new Customer("Customer3", "3-333-333-33-33");
        Customer customer4 = new Customer("Customer4", "4-444-444-44-44");

        Supplier supplier1 = new Supplier("Supplier1", "1-111-111-11-11");
        Supplier supplier2 = new Supplier("Supplier2", "1-111-111-11-11");
        Supplier supplier3 = new Supplier("Supplier3", "1-111-111-11-11");
        Supplier supplier4 = new Supplier("Supplier4", "1-111-111-11-11");

        Product product1 = new Product("Product1", supplier1, BigDecimal.valueOf(1000), true);
        Product product2 = new Product("Product2", supplier2, BigDecimal.valueOf(2000), false);
        Product product3 = new Product("Product3", supplier3, BigDecimal.valueOf(3000), true);
        Product product4 = new Product("Product4", supplier4, BigDecimal.valueOf(4000), false);

        Set<Product> productsOrder = new HashSet<>();
        Set<Product> productsOrder2 = new HashSet<>();
        productsOrder.add(product1);
        productsOrder.add(product2);
        productsOrder2.add(product3);
        productsOrder2.add(product4);

        Order order1 = new Order("Order1", customer1, currentDate, BigDecimal.valueOf(1000), productsOrder);
        Order order2 = new Order("Order2", customer2, currentDate, BigDecimal.valueOf(2000), productsOrder2);
        Order order3 = new Order("Order3", customer3, currentDate, BigDecimal.valueOf(3000), productsOrder);
        Order order4 = new Order("Order4", customer4, currentDate, BigDecimal.valueOf(4000), productsOrder2);

        //insert supplier
        crudOperationsSupplier.insertEntity(supplier1);
        crudOperationsSupplier.insertEntity(supplier2);
        crudOperationsSupplier.insertEntity(supplier3);
        crudOperationsSupplier.insertEntity(supplier4);

        //insert products
        crudOperationsProduct.insertEntity(product1);
        crudOperationsProduct.insertEntity(product2);
        crudOperationsProduct.insertEntity(product3);
        crudOperationsProduct.insertEntity(product4);

        //insert orders
        crudOperationsOrder.insertEntity(order1);
        crudOperationsOrder.insertEntity(order2);
        crudOperationsOrder.insertEntity(order3);
        crudOperationsOrder.insertEntity(order4);

        //insert customers
        crudOperationsCustomer.insertEntity(customer1);
        crudOperationsCustomer.insertEntity(customer2);
        crudOperationsCustomer.insertEntity(customer3);
        crudOperationsCustomer.insertEntity(customer4);

        //find customer
        Customer customer = crudOperationsCustomer.findEntity(1);
        System.out.println(customer.toString());

        //update customer
        customer.setPhone("0-000-000-00-00");
        crudOperationsCustomer.updateEntity(customer);
        System.out.println(customer.toString());

        //remove customer and order, order cascade delete
        Customer customer5 = new Customer("Customer5", "5-555-555-55-55");
        crudOperationsCustomer.insertEntity(customer5);
        int customerId = customer5.getCustomerId();
        Customer customerFound = crudOperationsCustomer.findEntity(customerId);
        System.out.println(customerFound.toString());

        Order order5 = new Order("Order5", customer5, currentDate, BigDecimal.valueOf(5000), productsOrder);
        crudOperationsOrder.insertEntity(order5);
        int orderId = order5.getOrderId();
        Order orderFound = crudOperationsOrder.findEntity(orderId);
        System.out.println(orderFound.toString());

        crudOperationsCustomer.removeEntity(customerId);

        try {
            Customer customerRemoved = crudOperationsCustomer.findEntity(customerId);
            System.out.println(customerRemoved.toString());
        } catch(Exception e) {
            System.out.println("Customer was removed");
        }

        try {
            Order orderRemoved = crudOperationsOrder.findEntity(orderId);
            crudOperationsOrder.removeEntity(orderId);
            System.out.println(orderRemoved.toString());
        } catch(Exception e) {
            System.out.println("Order was removed");
        }

        //find supplier
        Supplier supplier = crudOperationsSupplier.findEntity(1);
        System.out.println(supplier.toString());

        //update supplier
        supplier.setPhone("2-222-222-22-22");
        crudOperationsSupplier.updateEntity(supplier);
        System.out.println(supplier.toString());

        //remove supplier and product, product cascade delete
        Supplier supplier5 = new Supplier("Supplier5", "5-555-555-55-55");
        crudOperationsSupplier.insertEntity(supplier5);
        int supplierId = supplier5.getSupplierId();
        Supplier supplierFound = crudOperationsSupplier.findEntity(supplierId);
        System.out.println(supplierFound.toString());

        Product product5 = new Product("Product5", supplier5, BigDecimal.valueOf(5000), true);
        crudOperationsProduct.insertEntity(product5);
        int productId = product5.getProductId();
        Product productFound = crudOperationsProduct.findEntity(productId);
        System.out.println(productFound.toString());

        crudOperationsSupplier.removeEntity(supplierId);
        try {
            Supplier supplierRemoved = crudOperationsSupplier.findEntity(supplierId);
            System.out.println(supplierRemoved.toString());
        } catch(Exception e) {
            System.out.println("Supplier was removed");
        }

        try {
            Product productRemoved = crudOperationsProduct.findEntity(productId);
            crudOperationsProduct.removeEntity(productId);
            System.out.println(productRemoved.toString());
        } catch(Exception e) {
            System.out.println("Product was removed");
        }

        entityManager.close();
    }
}
