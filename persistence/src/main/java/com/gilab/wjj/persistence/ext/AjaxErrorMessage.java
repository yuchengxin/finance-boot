package com.gilab.wjj.persistence.ext;

/**
 * Created by yuankui on 10/31/17.
 */
public class AjaxErrorMessage {
    private int returnCode;
    private String errorMessage;

    public AjaxErrorMessage() {
    }

    public AjaxErrorMessage(int returnCode, String errorMessage) {
        this.returnCode = returnCode;
        this.errorMessage = errorMessage;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "AjaxErrorMessage{" +
                "returnCode=" + returnCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
