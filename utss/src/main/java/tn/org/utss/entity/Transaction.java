package tn.org.utss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tn.org.utss.entity.enums.TransactionType;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    TransactionType transactionType;
    String giver;
    String receiver;
    LocalDateTime date = LocalDateTime.now();
    @OneToOne
    @JsonIgnore @ToString.Exclude
    Transaction CanceledTransaction;
    String productDetails;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    User updatedBy;


}
