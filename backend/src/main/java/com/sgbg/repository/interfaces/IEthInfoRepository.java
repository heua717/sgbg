package com.sgbg.repository.interfaces;

import com.sgbg.domain.EthInfo;
import org.springframework.transaction.annotation.Transactional;

public interface IEthInfoRepository {
    EthInfo get(String ethUrl);

    @Transactional
    void put(String ethUrl, String blockNumber);
}
