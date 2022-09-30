package com.sgbg.blockchain.service.interfaces;

import com.sgbg.blockchain.domain.Wallet;

public interface IWalletService {

    // 인자 따로 줘야 함.
    void create (long userId, String password) throws Exception;
    void charge(long userId, long money) throws Exception;
    Wallet enterRoom (long userId, long roomId, long money) throws Exception;
    Wallet exitRoom (long userId, long roomId, long money) throws Exception;

    Wallet endRoom (long userId) throws Exception;

}
