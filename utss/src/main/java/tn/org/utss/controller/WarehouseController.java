package tn.org.utss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.org.utss.entity.Warehouse;
import tn.org.utss.interfaces.WarehouseService;

@RestController
@RequestMapping("/warehouse")
@CrossOrigin(origins = "*")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PutMapping("available/{idWarehouse}")
    public ResponseEntity<Warehouse> updateWarehouseAvailabilityState(@RequestBody boolean available,@PathVariable Long idWarehouse){
        Warehouse wh = warehouseService.UpdateWareHouseAvailabilityState( available, idWarehouse);

        return ResponseEntity.status(200).body(wh);
    }
}
