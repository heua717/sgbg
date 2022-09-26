package com.sgbg.service.interfaces;

import com.sgbg.domain.Purchase;
import org.springframework.transaction.annotation.Transactional;

public interface IEscrowContractService {
    @Transactional
    Purchase checkDeposit(int pid);
}
