package com.gilab.wjj.persistence.dao;

import com.gilab.wjj.persistence.model.Merchant;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface MerchantDao {
    Merchant getMerchant(long merchantId);

    List<Merchant> getMerchantWithFilter();

    long createMerchant(Merchant merchant);

    void updateMerchant(Merchant merchant);

    void deleteMerchant(long merchantId);
}
