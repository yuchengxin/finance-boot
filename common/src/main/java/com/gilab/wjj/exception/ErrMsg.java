package com.gilab.wjj.exception;

import java.text.MessageFormat;

/**
 * ErrMsg
 * Author: yuankui
 * Desc:
 * Change Log:
 * 2017/10/31 - created by yuankui
 */
public interface ErrMsg {
    ErrMsg.MsgPrefix getPrefix();

    int getErrCode();

    String getMsg();

    MessageFormat getFormat();

    public static enum MsgPrefix {
        COMMON;

        private MsgPrefix() {
        }
    }
}
