package com.gilab.wjj.exception;

/**
 * WorkflowRuntimeException
 * Author: zhe.jiang
 * Desc:
 * Change Log:
 * 10/20/16 - created by zhe.jiang
 */
public class FinanceRuntimeException extends RuntimeException {
    private ErrorMsg errorMsg;
    private String errCodeMsg;

    public FinanceRuntimeException(ErrMsg errMsg) {
        this((Throwable)null, (ErrMsg)errMsg);
    }

    public FinanceRuntimeException(ErrMsg errMsg, String... msgArgs) {
        this((Throwable)null, errMsg, msgArgs);
    }

    public FinanceRuntimeException(Throwable cause, ErrMsg errMsg) {
        this(cause, errMsg, new String[0]);
    }

    public FinanceRuntimeException(Throwable cause, ErrMsg errMsg, String... msgArgs) {
        super(ErrorMsg.format(errMsg, msgArgs), cause);
        this.errorMsg = new ErrorMsg(errMsg);
        this.errCodeMsg = this.errorMsg.getErrCodeMsg(msgArgs);
    }

    public ErrorMsg getErrorMsg() {
        return this.errorMsg;
    }

    public String getErrCodeMsg() {
        return this.errCodeMsg;
    }

}
