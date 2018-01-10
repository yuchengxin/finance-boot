package com.gilab.wjj.persistence.handler;

import com.gilab.wjj.persistence.IntEnumTypeHandler;
import com.gilab.wjj.persistence.model.SigningStatus;

/**
 * Created by yuankui on 1/8/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class SigningStatusHandler extends IntEnumTypeHandler<SigningStatus> {
    public SigningStatusHandler() {
        super(SigningStatus.class);
    }
}
