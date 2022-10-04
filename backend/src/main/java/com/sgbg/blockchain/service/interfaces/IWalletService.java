package com.sgbg.blockchain.service.interfaces;

import com.sgbg.blockchain.domain.Wallet;
import com.sgbg.blockchain.domain.WalletHistory;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWalletService {

    // 인자 따로 줘야 함.
    Wallet createWallet(long userId, String password) throws Exception;

    Wallet charge(long userId, long money) throws Exception;

    void checkWallet(long userId) throws Exception;

    Wallet getWallet(long userId, String password) throws Exception;

    List<WalletHistory> getHistoryList(long userId) throws Exception;
}
