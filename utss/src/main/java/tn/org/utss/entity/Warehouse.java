package tn.org.utss.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Warehouse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String addressName;
    String phone;
    boolean available = true;

    @OneToMany(mappedBy = "warehouse") @JsonIgnore @ToString.Exclude
    List<ProductWarehouse> products = new ArrayList<>();



}
