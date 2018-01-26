package com.gilab.wjj.persistence.handler;

import com.gilab.wjj.persistence.IntEnumTypeHandler;
import com.gilab.wjj.persistence.model.RentMonthMode;

/**
 * Created by yuankui on 1/26/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class RentMonthModeHandler extends IntEnumTypeHandler<RentMonthMode> {
    public RentMonthModeHandler() {
        super(RentMonthMode.class);
    }
}
