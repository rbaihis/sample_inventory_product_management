package tn.org.utss.interfaces;

import tn.org.utss.entity.User;
import tn.org.utss.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
//    Warehouse CreateWarehouse(Warehouse warehouse ,  User connectedUser);

    List<Warehouse> getAllWarehouses();

    Warehouse UpdateWareHouseAvailabilityState( boolean available, Long idWarehouse);
}
