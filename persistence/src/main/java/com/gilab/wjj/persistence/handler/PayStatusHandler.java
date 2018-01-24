package com.gilab.wjj.persistence.handler;

import com.gilab.wjj.persistence.IntEnumTypeHandler;
import com.gilab.wjj.persistence.model.PayStatus;

/**
 * Created by che on 2018/1/24.
 */
public class PayStatusHandler extends IntEnumTypeHandler<PayStatus> {
    public PayStatusHandler() {
        super(PayStatus.class);
    }
}
