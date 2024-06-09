package tn.org.utss.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.org.utss.entity.*;
import tn.org.utss.interfaces.CategoryService;
import tn.org.utss.interfaces.ProductWarehouseService;
import tn.org.utss.interfaces.SubCategoryService;
import tn.org.utss.repository.ProductRepo;
import tn.org.utss.repository.ProductWarehouseRepo;
import tn.org.utss.repository.WarehouseRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Getter
@Setter

public class ProductWarehouseImplementationA  implements ProductWarehouseService {



    private final ProductWarehouseRepo pwRepo;
    private final WarehouseRepo whRepo;
    private final ProductRepo prodRepo;
    @Autowired
    private SubCategoryService subCategoryService;


    @Autowired
    public ProductWarehouseImplementationA(ProductWarehouseRepo pwRepo, WarehouseRepo whRepo, ProductRepo prodRepo) {
        this.pwRepo = pwRepo;
        this.whRepo = whRepo;
        this.prodRepo = prodRepo;
    }






    /**
     * related to transaction should be called that time
     * @param idWarehouse Long
     * @param idProduct Long
     * @param quantity Integer
     * @param expirationDate LocalDate
     * @param connectedUser User
     * @return ProductWarehouse after updated
     */
    @Override
    public ProductWarehouse updateProductWarehouse(Long idWarehouse , Long idProduct , Integer quantity , LocalDate expirationDate , User connectedUser){
        return pwRepo.updateQuantityExpDateLastUpdateUpdatedBy(idProduct,idWarehouse,quantity,expirationDate,connectedUser, LocalDateTime.now()).orElse(null);
    }

    /**
     * actions should be made by warehouse_Keeper to update expiration date of products
     * @return ProductWarehouseUpdated
     */
    @Override
    public ProductWarehouse updateProductWarehouseExpirationDate(Long pwId, LocalDate expDate , User connUser){
        return pwRepo.updateProductWarehouseExpirationDate(pwId,expDate,connUser).orElse(null);
    }




    @Override
    public Warehouse CreateWarehouse(Warehouse warehouse, User connectedUser){
        return createProductWarehouseForANewWarehouse(
                whRepo.save(warehouse),
                prodRepo.findAll(),
                connectedUser
        );
    }
    @Override
    public Warehouse createProductWarehouseForANewWarehouse(Warehouse warehouse, List<Product> products, User connUser){

        List<ProductWarehouse>  productWarehouses = new ArrayList<>();
        for (Product product : products) {

            productWarehouses.add(
                    ProductWarehouse.builder()
                            .warehouse(warehouse)
                            .idWarehouse(warehouse.getId())
                            .nameWarehouse(warehouse.getAddressName())
                            .product(product)
                            .idProduct(product.getId())
                            .nameProduct(product.getName())
                            .quantity(0)
                            .expirationDate(null)
                            .updatedBy(connUser)
                            .lastUpdated(LocalDateTime.now())
                            .build()
            );
        }

        warehouse.setProducts(pwRepo.saveAll(productWarehouses));
        return warehouse;
    }


    @Override
    public Product createProduct(Product product ,Long idSubCategory,  User connectedUser){
        SubCategory subCategory= subCategoryService.getSubCategoryById(idSubCategory);
        product.setSubCategory(subCategory);

        return createProductWarehouseForANewProduct(
                prodRepo.save(product),
                whRepo.findAll(),
                connectedUser
        );
    }
    @Override
    public Product createProductWarehouseForANewProduct(Product product, List<Warehouse> warehouses, User connUser) {

        List<ProductWarehouse> productWarehouses = new ArrayList<>();
        for (Warehouse warehouse : warehouses) {
            productWarehouses.add(
                    ProductWarehouse.builder()
                            .warehouse(warehouse)
                            .idWarehouse(warehouse.getId())
                            .nameWarehouse(warehouse.getAddressName())
                            .product(product)
                            .idProduct(product.getId())
                            .nameProduct(product.getName())
                            .quantity(0)
                            .productNature(product.getProductNature())
                            .expirationDate(null)
                            .updatedBy(connUser)
                            .lastUpdated(LocalDateTime.now())
                            .build()
            );
        }
        product.setWarehouses( pwRepo.saveAll(productWarehouses) );
        return product;
    }










    //***** use this when manually adding warehouses and products from db to update state
    @Override
    public Long upToDateProductsWithAllWarehouses() {
        List<Warehouse> whs = whRepo.findAll();
        List<Product> ps= prodRepo.findAll();
        //hashset for uniqueness purpose
        HashSet<ProductWarehouse> uniquePws = new HashSet<>(pwRepo.findAll());
        long countInitially = (long) uniquePws.size();
        for (Warehouse wh : whs)
            for (Product p : ps) {
                uniquePws.add(
                    ProductWarehouse.builder()
                            .warehouse(wh)
                            .idWarehouse(wh.getId())
                            .nameWarehouse(wh.getAddressName())
                            .product(p)
                            .idProduct(p.getId())
                            .nameProduct(p.getName())
                            .quantity(0)
                            .productNature(p.getProductNature())
                            .expirationDate(null)
                            .updatedBy(null)
                            .lastUpdated(null)
                            .build()
                );

            }
        countInitially -= uniquePws.size();
        if(countInitially != 0)
            pwRepo.saveAll(uniquePws);

        return countInitially;
    }




}
