package com.sgbg.api.response;

import com.sgbg.blockchain.domain.Transaction;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TransactionListRes {

    @Schema(name = "transactions", description = "트랜잭션 목록")
    private List<TransactionRes> transactions;

    public static TransactionListRes of(List<Transaction> transactionList) {
        TransactionListRes res = new TransactionListRes();
        res.setTransactions(transactionList);
        return res;
    }

    public void setTransactions(List<Transaction> transactionList) {
        for (int i = 0; i < transactionList.size(); i++) {
//            transactionList.get(i)
        }
    }

}
