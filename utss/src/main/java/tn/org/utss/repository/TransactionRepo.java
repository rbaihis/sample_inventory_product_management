package tn.org.utss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.org.utss.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction,Long> {


}
