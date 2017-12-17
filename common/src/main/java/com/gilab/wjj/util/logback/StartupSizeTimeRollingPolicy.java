package com.gilab.wjj.util.logback;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import ch.qos.logback.core.rolling.RolloverFailure;
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP;

/**
 * StartupSizeTimeRollingPolicy
 * Author: yuankui
 * Desc:
 *
 * logback rolling policy based on startup/size/time
 *
 * Change Log:
 * 2017/10/31 - created by yuankui
 */
@NoAutoStart
public class StartupSizeTimeRollingPolicy<E> extends SizeAndTimeBasedFNATP<E> {
    @Override
    public void start() {
        super.start();
        nextCheck = 0L;
        isTriggeringEvent(null, null);
        try {
            tbrp.rollover();
        } catch (RolloverFailure e) {
            // do nothing
        }
    }
}
