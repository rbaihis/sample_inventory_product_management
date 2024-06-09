package tn.org.utss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.org.utss.entity.Warehouse;

import java.util.Optional;

public interface WarehouseRepo extends JpaRepository<Warehouse,Long> {

    @Query("update Warehouse w set w.available = :available where w.id = :id")
    Optional<Warehouse> updateWarehouseAvailability(boolean available, Long id);


}
