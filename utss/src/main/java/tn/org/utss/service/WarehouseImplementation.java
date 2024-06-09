package tn.org.utss.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.org.utss.entity.User;
import tn.org.utss.entity.Warehouse;
import tn.org.utss.interfaces.ProductService;
import tn.org.utss.interfaces.ProductWarehouseService;
import tn.org.utss.interfaces.WarehouseService;
import tn.org.utss.repository.WarehouseRepo;

import java.util.List;

@Service
@Getter
@Setter

public class WarehouseImplementation implements WarehouseService {

    private final WarehouseRepo whRepo;


    @Autowired
    public WarehouseImplementation(WarehouseRepo whRepo) {
        this.whRepo = whRepo;

    }


    @Override
    public List<Warehouse> getAllWarehouses(){
        return whRepo.findAll();
    }

    @Override
    public Warehouse UpdateWareHouseAvailabilityState(boolean available, Long idWarehouse) {
        return whRepo.updateWarehouseAvailability(available,idWarehouse).orElse(null);
    }
}
