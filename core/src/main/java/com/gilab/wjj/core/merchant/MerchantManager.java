package com.gilab.wjj.core.merchant;

import com.gilab.wjj.core.MerchantAgent;
import com.gilab.wjj.persistence.dao.MerchantDao;
import com.gilab.wjj.persistence.model.Merchant;
import com.gilab.wjj.persistence.model.ReqResult;
import com.gilab.wjj.persistence.model.SimpleReqResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Service("merchantAgent")
public class MerchantManager implements MerchantAgent {

    @Autowired
    private MerchantDao merchantDao;

    @Override
    public ReqResult<Merchant> getMerchant(long merchantId) {
        Merchant merchant = merchantDao.getMerchant(merchantId);
        if (merchant == null) {
            return ReqResult.fail("Cannot find merchant[%d]", merchantId);
        } else {
            return ReqResult.success(merchant, "merchant found.");
        }
    }

    @Override
    public List<Merchant> getMerchantWithFilter(String merchantName, String merchantPhone, String merchantIdNo, String bankAccount) {
        return merchantDao.getMerchantWithFilter(merchantName, merchantPhone, merchantIdNo, bankAccount);
    }

    @Override
    public ReqResult<Merchant> createMerchant(Merchant merchant) {
        long id = merchantDao.createMerchant(merchant);
        Merchant newMerchant = merchant.clone();
        newMerchant.setId(id);
        return ReqResult.success(newMerchant, "merchant[%d] created.", id);
    }

    @Override
    public SimpleReqResult updateMerchant(Merchant merchant) {
        Merchant origMerchant = merchantDao.getMerchant(merchant.getId());
        if (origMerchant == null) return SimpleReqResult.fail("Cannot find merchant[%d]", merchant.getId());
        merchantDao.updateMerchant(merchant);
        return SimpleReqResult.success("merchant[%d] updated.", merchant.getId());
    }

    @Override
    public SimpleReqResult deleteMerchant(long merchantId) {
        Merchant merchant = merchantDao.getMerchant(merchantId);
        if (merchant == null) return SimpleReqResult.fail("Cannot find merchant[%d]", merchantId);
        merchantDao.deleteMerchant(merchantId);
        return SimpleReqResult.success("merchant[%d] removed.", merchantId);
    }
}
