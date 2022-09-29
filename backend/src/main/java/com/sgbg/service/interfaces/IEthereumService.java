package com.sgbg.service.interfaces;

import com.sgbg.domain.Address;
import java.math.BigInteger;

public interface IEthereumService {
    String requestEth(String address);
    BigInteger getBalance(String address);

    Address getAddress(String address);
}
