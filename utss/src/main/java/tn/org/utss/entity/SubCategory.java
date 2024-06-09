package tn.org.utss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @ManyToOne @JsonIgnore @ToString.Exclude
    Category category;

    @OneToMany(mappedBy = "subCategory" , cascade = CascadeType.PERSIST)
    List<Product> products;
}
