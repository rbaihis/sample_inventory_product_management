package tn.org.utss.interfaces;

import tn.org.utss.entity.Product;
import tn.org.utss.entity.ProductWarehouse;
import tn.org.utss.entity.User;
import tn.org.utss.entity.Warehouse;

import java.time.LocalDate;
import java.util.List;

public interface ProductWarehouseService {

    ProductWarehouse updateProductWarehouse(Long idWarehouse, Long idProduct, Integer quantity, LocalDate expirationDate, User connectedUser);

    Warehouse createProductWarehouseForANewWarehouse(Warehouse warehouse, List<Product> products, User connUser);

    Product createProductWarehouseForANewProduct(Product product, List<Warehouse> warehouses, User connUser);

    Warehouse CreateWarehouse(Warehouse warehouse, User connectedUser);

    Product createProduct(Product product,Long idSubCategory, User connectedUser);


    Long upToDateProductsWithAllWarehouses();



    ProductWarehouse updateProductWarehouseExpirationDate(Long pwId, LocalDate expDate, User connUser);
}
