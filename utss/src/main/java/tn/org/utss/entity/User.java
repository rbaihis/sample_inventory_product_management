package tn.org.utss.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import tn.org.utss.entity.enums.Role;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String lastName;
    @Enumerated(EnumType.STRING)
    Role role;

    @Column(unique = true,nullable = false)
    String email;
    String password;

    @OneToMany
    List<Warehouse> assignedWarehouses;

}
