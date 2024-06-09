package tn.org.utss.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import tn.org.utss.dto.TransactionDto;
import tn.org.utss.entity.Transaction;
import tn.org.utss.entity.User;

public interface TransactionService {

    Transaction AddTransaction(TransactionDto transactionDto, User ConnectedUser) throws JsonProcessingException;

}
