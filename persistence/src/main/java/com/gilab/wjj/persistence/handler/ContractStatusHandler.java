package com.gilab.wjj.persistence.handler;

import com.gilab.wjj.persistence.IntEnumTypeHandler;
import com.gilab.wjj.persistence.model.ContractStatus;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class ContractStatusHandler extends IntEnumTypeHandler<ContractStatus> {
    public ContractStatusHandler() {
        super(ContractStatus.class);
    }
}
