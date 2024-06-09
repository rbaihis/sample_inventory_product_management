package tn.org.utss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.*;
import tn.org.utss.entity.enums.ProductNature;
import tn.org.utss.entity.enums.UnityProduct;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    Integer quantity;
    @Enumerated(EnumType.STRING)
    UnityProduct unit;
    @Enumerated(EnumType.STRING)
    ProductNature productNature;
    String imageUri;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore @ToString.Exclude
    SubCategory subCategory;

    @OneToMany(mappedBy = "product") @JsonIgnore
    @ToString.Exclude
    List<ProductWarehouse> warehouses;



}
