package com.sgbg.service.interfaces;

import com.sgbg.domain.Record;

import java.util.List;

public interface IPurchaseRecordContractService {
    List<Record> getHistory(final String escrowContractAddress);
}
