package tn.org.utss.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.org.utss.dto.TransactionDto;
import tn.org.utss.entity.Transaction;
import tn.org.utss.entity.User;
import tn.org.utss.interfaces.TransactionService;
import tn.org.utss.service.utilData.UserFakeData;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService tranService;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody TransactionDto transDto) throws JsonProcessingException {

        User connUser = UserFakeData.user1agent;
        Transaction trans = tranService.AddTransaction(transDto , connUser);

        return ResponseEntity.status(201).body(trans);
    }

}
