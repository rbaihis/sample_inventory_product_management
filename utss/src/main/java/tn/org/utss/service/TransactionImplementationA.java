package tn.org.utss.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.org.utss.dto.ProductDto;
import tn.org.utss.dto.TransactionDto;
import tn.org.utss.dto.WarehouseDto;
import tn.org.utss.entity.ProductWarehouse;
import tn.org.utss.entity.Transaction;
import tn.org.utss.entity.User;
import tn.org.utss.entity.enums.TransactionType;
import tn.org.utss.interfaces.ProductWarehouseService;
import tn.org.utss.interfaces.TransactionService;
import tn.org.utss.repository.TransactionRepo;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Getter
@Setter

public class TransactionImplementationA  implements TransactionService {
    private final TransactionRepo transactionRepo;
    private final ProductWarehouseService pwService;
    @Autowired
    public TransactionImplementationA(TransactionRepo transactionRepo, ProductWarehouseService pwService) {
        this.transactionRepo = transactionRepo;
        this.pwService = pwService;
    }

    @Override
    @Transactional
    public Transaction AddTransaction(TransactionDto tranDto , User connectedUser) throws JsonProcessingException {


        Transaction transaction = tranDto.getTransaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setUpdatedBy(connectedUser);

        List<ProductWarehouse> productWarehouses = tranDto.getProductWarehouses();
        transaction.setProductDetails(
                new ObjectMapper().writeValueAsString(
                            ProductDto.transformToProductDto(tranDto.getProductWarehouses()
                        )
                )
        );

        //updating the ProductWarehouse
        for (ProductWarehouse pw : tranDto.getProductWarehouses()) {
            //make sure that quantity is negative when using DONATION_OUTPUT and CANCEL
            if(transaction.getTransactionType().equals(TransactionType.CANCEL) || transaction.getTransactionType().equals(TransactionType.DONATION_OUTPUT  )){
                if(pw.getQuantity()>0)
                    pw.setQuantity( -1 * pw.getQuantity() );
            }
            pwService.updateProductWarehouse(pw.getIdWarehouse(), pw.getIdProduct(), pw.getQuantity(), pw.getExpirationDate(), connectedUser);
        }

        return transaction;
    }



    public ResponseEntity<Transaction> CancelTransaction(Transaction transToCancel, User connUser) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductDto> productDtos = objectMapper.readValue(transToCancel.getProductDetails(), new TypeReference<List<ProductDto>>(){});

        //update the old transaction dataDetails + update the db with removing products added
        for (ProductDto pDto : productDtos) {
            for (WarehouseDto wh: pDto.getWarehouses()) {
                wh.setQuantity((-1)* wh.getQuantity());
                wh.setLastUpdated(LocalDateTime.now());
                pwService.updateProductWarehouse(
                        wh.getId(),
                        pDto.getId(),
                        wh.getQuantity(),
                        null,
                        connUser
                );
            }
        }

        Transaction trans = Transaction.builder()
                .date(LocalDateTime.now())
                .updatedBy(connUser)
                .giver(transToCancel.getGiver())
                .receiver(transToCancel.getReceiver())
                .transactionType(TransactionType.CANCEL)
                .CanceledTransaction(transToCancel)
                .productDetails(objectMapper.writeValueAsString(productDtos))
                .build();


        return  ResponseEntity.status(201).body(null);

    }


}
