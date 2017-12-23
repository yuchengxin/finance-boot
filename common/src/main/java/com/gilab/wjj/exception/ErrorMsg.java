package com.gilab.wjj.exception;
import java.text.MessageFormat;

/**
 * Created by yuankui on 12/19/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */

class ErrorMsg {
    private ErrMsg concreteErrMsg;

    public ErrorMsg(ErrMsg concreteErrMsg) {
        this.concreteErrMsg = concreteErrMsg;
    }

    public String format(String... reasons) {
        return format(this.concreteErrMsg, reasons);
    }

    public String getErrCodeMsg() {
        return getErrCodeMsg(this.concreteErrMsg);
    }

    public String getErrCodeMsg(String... reasons) {
        return getErrCodeMsg(this.concreteErrMsg, reasons);
    }

    public static String format(ErrMsg errMsg, String... reasons) {
        if(reasons != null && reasons.length != 0) {
            MessageFormat format = errMsg.getFormat();
            if(format != null) {
                return format.format(reasons);
            } else if(reasons.length > 1) {
                StringBuilder sb = new StringBuilder();
                String[] arr$ = reasons;
                int len$ = reasons.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String re = arr$[i$];
                    if(re != null) {
                        if(sb.length() > 0) {
                            sb.append(" ");
                        }

                        sb.append(re);
                    }
                }

                return errMsg.getMsg() + " " + sb.toString();
            } else {
                return errMsg.getMsg() + " " + reasons[0];
            }
        } else {
            return errMsg.getMsg();
        }
    }

    public static String getErrCodeMsg(ErrMsg errMsg) {
        return "[Error " + errMsg.getPrefix().name() + errMsg.getErrCode() + "]: " + errMsg.getMsg();
    }

    public static String getErrCodeMsg(ErrMsg errMsg, String... reasons) {
        return "[Error " + errMsg.getPrefix().name() + errMsg.getErrCode() + "]: " + format(errMsg, reasons);
    }
}