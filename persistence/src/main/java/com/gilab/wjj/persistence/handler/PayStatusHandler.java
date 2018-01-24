package com.gilab.wjj.persistence.handler;

import com.gilab.wjj.persistence.IntEnumTypeHandler;
import com.gilab.wjj.persistence.model.PayStatus;

/**
 * Created by yuankui on 1/24/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class PayStatusHandler extends IntEnumTypeHandler<PayStatus> {
    public PayStatusHandler() {
        super(PayStatus.class);
    }
}
