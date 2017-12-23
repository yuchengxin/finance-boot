package com.gilab.wjj.persistence.spring;

import com.gilab.wjj.persistence.dao.MerchantDao;
import com.gilab.wjj.persistence.mapper.MerchantMapper;
import com.gilab.wjj.persistence.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Repository("MerchantDao")
public class MerchantDaoImpl implements MerchantDao {
    @Autowired
    private MerchantMapper mapper;

    @Override
    public Merchant getMerchant(long merchantId) {
        return mapper.selectMerchant(merchantId);
    }

    @Override
    public List<Merchant> getMerchantWithFilter() {
        return mapper.selectMerchantWithFilter();
    }

    @Override
    public long createMerchant(Merchant merchant) {
        mapper.insertMerchant(merchant);
        return merchant.getId();
    }

    @Override
    public void updateMerchant(Merchant merchant) {
        mapper.updateMerchant(merchant);
    }

    @Override
    public void deleteMerchant(long merchantId) {
        mapper.deleteMerchant(merchantId);
    }
}
