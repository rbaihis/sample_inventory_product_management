package tn.org.utss.dto;

import lombok.*;
import tn.org.utss.entity.ProductWarehouse;
import tn.org.utss.entity.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionDto {
    Transaction transaction;
    List<ProductWarehouse> ProductWarehouses = new ArrayList<>();
}
