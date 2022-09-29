package com.sgbg.repository.interfaces;

import com.sgbg.domain.Transaction;

import java.util.List;

public interface ITransactionRepository {
    List<Transaction> list();
    Transaction get(String hash);
    List<Transaction> getByAddress(String address);
    long add(Transaction tx);
}
