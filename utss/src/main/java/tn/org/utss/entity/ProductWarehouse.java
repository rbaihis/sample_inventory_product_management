package tn.org.utss.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.org.utss.entity.enums.ProductNature;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table( uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id", "warehouse_id"})
})
public class ProductWarehouse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer quantity;
    LocalDate expirationDate;
    LocalDateTime lastUpdated;
    Long idProduct;
    String nameProduct;
    ProductNature productNature;
    Long idWarehouse;
    String nameWarehouse;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "product_id", nullable = false)
    Product product;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "warehouse_id", nullable = false)
    Warehouse warehouse;

    @ManyToOne
    User updatedBy;

    // necessary to avoid duplication in insert in certain scenarios
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ProductWarehouse that)) return false;
        return Objects.equals(warehouse.getId(), that.warehouse.getId()) && Objects.equals(product.getId(), that.product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouse.getId(), product.getId());
    }
}
