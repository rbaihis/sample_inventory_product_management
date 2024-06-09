package tn.org.utss.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WarehouseDto {
        private Long id;
        private String warehouseName;
        private Integer quantity;
        private LocalDate expirationDate;
        private LocalDateTime lastUpdated;

}

