package com.sgbg.blockchain.repository;

import com.sgbg.blockchain.domain.Transaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @NotNull
    Optional<Transaction> findById(@NotNull Long id);
}
