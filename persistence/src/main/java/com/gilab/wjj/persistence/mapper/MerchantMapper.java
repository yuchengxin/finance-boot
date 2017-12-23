package com.gilab.wjj.persistence.mapper;

import com.gilab.wjj.persistence.model.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Mapper
public interface MerchantMapper {
    Merchant selectMerchant(@Param("id") long merchantId);

    List<Merchant> selectMerchantWithFilter(@Param("namePattern") String namePattern,
                                            @Param("merchantPhone") String merchantPhone,
                                            @Param("merchantIdNo") String merchantIdNo,
                                            @Param("bankAccount") String bankAccount);

    void insertMerchant(Merchant merchant);

    void updateMerchant(Merchant merchant);

    void deleteMerchant(@Param("id") long merchantId);
}
