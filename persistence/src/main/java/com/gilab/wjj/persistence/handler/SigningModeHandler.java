package com.gilab.wjj.persistence.handler;

import com.gilab.wjj.persistence.IntEnumTypeHandler;
import com.gilab.wjj.persistence.model.SigningMode;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class SigningModeHandler extends IntEnumTypeHandler<SigningMode> {
    public SigningModeHandler() {
        super(SigningMode.class);
    }
}
