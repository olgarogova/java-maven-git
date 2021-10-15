package jpa_task.impl.domain.services;

import jpa_task.impl.domain.ServiceConfig;
import jpa_task.impl.domain.entity.Customer;
import jpa_task.impl.domain.entity.Order;
import jpa_task.impl.domain.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
class MyServiceTest {

    @Autowired
    private MyService myService;

    OrderService orderService = mock(OrderService.class);
    SupplierService supplierService = mock(SupplierService.class);

    Product product1;
    Customer customer1;
    Set<Product> productsOrder;
    Order order1;

    @Test
    void ensureThatOrderFulfilled() {
        myService = new MyService(orderService, supplierService);
        doNothing().when(orderService).createNewOrder(order1);
        doNothing().when(orderService).addProductToOrder(product1, order1);
        doNothing().when(orderService).buyProduct(customer1, product1);
        doNothing().when(supplierService).insertProducts(productsOrder);
        myService.fulfillOrder(order1, product1, productsOrder, customer1);
        verify(orderService,times(1)).createNewOrder(order1);
        verify(orderService,times(1)).addProductToOrder(product1, order1);
        verify(orderService,times(1)).buyProduct(customer1, product1);
        verify(supplierService,times(1)).insertProducts(productsOrder);
    }
}