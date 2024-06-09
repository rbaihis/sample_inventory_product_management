package tn.org.utss.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.org.utss.entity.ProductWarehouse;
import tn.org.utss.entity.enums.ProductNature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private ProductNature productNature;
    private List<WarehouseDto> warehouses;


    public static List<ProductDto> transformToProductDto(List<ProductWarehouse> productWarehouses) {
        Map<Long, ProductDto> productMap = new HashMap<>();

        for (ProductWarehouse pw : productWarehouses) {
            ProductDto productDTO = productMap.computeIfAbsent(pw.getIdProduct(), id ->
                    new ProductDto(pw.getIdProduct(), pw.getNameProduct() , pw.getProductNature(), new ArrayList<>())
            );

            WarehouseDto warehouseDTO = new WarehouseDto(
                    pw.getId(),
                    pw.getNameWarehouse(),
                    pw.getQuantity(),
                    pw.getExpirationDate(),
                    pw.getLastUpdated()
            );

            productDTO.getWarehouses().add(warehouseDTO);
        }
        return new ArrayList<>(productMap.values());
    }


}
