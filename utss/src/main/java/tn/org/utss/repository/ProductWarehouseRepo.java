package tn.org.utss.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tn.org.utss.entity.ProductWarehouse;
import tn.org.utss.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductWarehouseRepo  extends JpaRepository<ProductWarehouse,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE  ProductWarehouse pw set pw.quantity = pw.quantity + :quantity , "+
            "pw.lastUpdated = :lastUpdated ,"+
            "pw.updatedBy = :connectedUser ,"+
            "pw.expirationDate = case when (pw.expirationDate Is null or :expDate < pw.expirationDate) then :expDate else pw.expirationDate END "+
            "where pw.product.id = :productId and pw.warehouse.id = :warehouseId ")
    Optional<ProductWarehouse> updateQuantityExpDateLastUpdateUpdatedBy(
            Long productId, Long warehouseId, Integer quantity, LocalDate expDate ,
            User connectedUser , LocalDateTime lastUpdated);


    @Modifying
    @Transactional
    @Query("update  ProductWarehouse  pw " +
            "set pw.expirationDate = :expDate , pw.lastUpdated = CURRENT_TIMESTAMP  , pw.updatedBy = :connUser " +
            "where pw.id = :pwId")
    Optional<ProductWarehouse> updateProductWarehouseExpirationDate(Long pwId , LocalDate expDate , User connUser);

}
