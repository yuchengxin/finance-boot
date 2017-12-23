package com.gilab.wjj.core;

import com.gilab.wjj.persistence.model.Merchant;
import com.gilab.wjj.persistence.model.ReqResult;
import com.gilab.wjj.persistence.model.SimpleReqResult;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface MerchantAgent extends Agent {

    ReqResult<Merchant> getMerchant(long merchantId);

    ReqResult<Merchant> getMerchantWithFilter();

    ReqResult<Merchant> createMerchant(Merchant merchant);

    SimpleReqResult updateMerchant(Merchant merchant);

    SimpleReqResult deleteMerchant(long merchantId);
}
