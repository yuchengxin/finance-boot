package com.gilab.wjj.persistence.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yuankui on 10/31/17.
 */
public class ReqResult<T> implements Result {
    private final boolean success;
    private final T result;
    private final String message;

    @JsonCreator
    public ReqResult(
            @JsonProperty("message") String message,
            @JsonProperty("success") boolean success,
            @JsonProperty("result") T result
    ) {
        this.message = message;
        this.success = success;
        this.result = result;
    }

    ReqResult(boolean success, T result, String messageTemplate, Object... params) {
        this.success = success;
        this.result = result;
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

    public T getResult() {
        return result;
    }

    public SimpleReqResult asSimple() {
        return SimpleReqResult.create(success, message);
    }

    /* factory methods */

    public static <T> ReqResult<T> create(boolean success, T result, String messageTemplate, Object... params) {
        return new ReqResult<>(success, result, messageTemplate, params);
    }

    public static <T> ReqResult<T> success(T result, String messageTemplate, Object... params) {
        return new ReqResult<>(true, result, messageTemplate, params);
    }

    public static <T> ReqResult<T> fail(String messageTemplate, Object... params) {
        return new ReqResult<>(false, null, messageTemplate, params);
    }

    private static ReqResult emptySuccessInstance = success(null, null);

    @SuppressWarnings("unchecked")
    public static <T> ReqResult<T> emptySuccess() {
        return emptySuccessInstance;
    }

    @Override
    public String toString() {
        return "ReqResult{" +
                "success=" + success +
                ", result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
