package com.sgbg.repository.interfaces;

import com.sgbg.domain.Purchase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPurchaseRepository {
    List<Purchase> list();
    Purchase get(long id);
    Purchase getByPurchaseId(long id);
    List<Purchase> getBySeller(long id);
    List<Purchase> getByBuyer(long id);

    @Transactional
    long create(Purchase purchase);

    @Transactional
    long update(Purchase purchase);
}
