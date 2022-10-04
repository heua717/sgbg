package com.sgbg.blockchain.service.interfaces;

import com.sgbg.blockchain.domain.Wallet;

public interface ISingleBungleService {
    String createRoom(long hostId, long duration, long minimumAmount) throws Exception;

    Wallet enterRoom(long userId, long hostId, String sgbgContractAddress, long money) throws Exception;

    Wallet exitRoom(long userId, long hostId, String sgbgContractAddress, long money) throws Exception;

    Wallet endRoom(long roomId, long hostId, String sgbgContractAddress) throws Exception;

    Wallet isSuccess(long userId, boolean isSuccess, long hostId, String sgbgContractAddress) throws Exception;
}
