package jpa_task.impl.domain.services;

import jpa_task.impl.domain.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SupplierService {

    public void insertProducts(Set<Product> products){
        System.out.println("Products added!");
    }
}
