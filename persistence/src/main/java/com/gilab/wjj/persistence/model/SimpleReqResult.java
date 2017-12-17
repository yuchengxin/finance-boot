package com.gilab.wjj.persistence.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yuankui on 10/31/17.
 */
public class SimpleReqResult implements Result {
    private final boolean success;
    private final String message;

    @JsonCreator
    public SimpleReqResult(
            @JsonProperty("message") String message,
            @JsonProperty("success") boolean success) {
        this.message = message;
        this.success = success;
    }

    SimpleReqResult(boolean success, String messageTemplate, Object... params) {
        this.success = success;
        this.message = messageTemplate == null ? null : String.format(messageTemplate, params);
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static SimpleReqResult create(boolean success, String messageTemplate, Object... params) {
        return new SimpleReqResult(success, messageTemplate, params);
    }

    public static SimpleReqResult success(String messageTemplate, Object... params) {
        return new SimpleReqResult(true, messageTemplate, params);
    }

    public static SimpleReqResult fail(String messageTemplate, Object... params) {
        return new SimpleReqResult(false, messageTemplate, params);
    }

    private static final SimpleReqResult emptySuccessInstance = success(null);

    public static SimpleReqResult emptySuccess() {
        return emptySuccessInstance;
    }

    @Override
    public String toString() {
        return "SimpleReqResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
