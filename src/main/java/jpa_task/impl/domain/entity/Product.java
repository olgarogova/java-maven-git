package jpa_task.impl.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", updatable = false, nullable = false)
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "is_discontinued", columnDefinition="boolean default true")
    private boolean isDiscontinued;

    @ManyToMany (mappedBy = "orderProducts")
    Set<Order> orders;

    public Product() {
    }

    public Product(String productName, Supplier supplier, BigDecimal unitPrice, boolean isDiscontinued) {
        this.productName = productName;
        this.supplier = supplier;
        this.unitPrice = unitPrice;
        this.isDiscontinued = isDiscontinued;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isDiscontinued() {
        return isDiscontinued;
    }

    public void setDiscountinued(boolean discontinued) {
        isDiscontinued = discontinued;
    }

    @Override
    public String toString() {
        return "Product {" +
                "productId = " + productId +
                ", productName = '" + productName + '\'' +
                ", supplier = " + supplier +
                ", unitPrice = " + unitPrice +
                ", isDiscontinued = " + isDiscontinued +
                '}';
    }
}
