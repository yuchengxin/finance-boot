package com.gilab.wjj.persistence.mapper;

import com.gilab.wjj.persistence.model.Contract;
import com.gilab.wjj.persistence.model.ContractStatus;
import com.gilab.wjj.persistence.model.SigningMode;
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
public interface ContractMapper {
    Contract selectContract(@Param("id") long contractId);

    List<Contract> selectContractWithFilter(@Param("filterStartTime") Long filterStartTime,
                                            @Param("filterEndTime") Long filterEndTime,
                                            @Param("contractVersion") String contractVersion,
                                            @Param("buildingStartSize") Double buildingStartSize,
                                            @Param("buildingEndSize") Double buildingEndSize,
                                            @Param("signingMode") SigningMode signingMode,
                                            @Param("status") ContractStatus status);

    void insertContract(Contract contract);

    void updateContract(Contract contract);

    void deleteContract(@Param("id") long contractId);
}
