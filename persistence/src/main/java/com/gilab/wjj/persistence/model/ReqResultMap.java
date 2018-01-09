package com.gilab.wjj.persistence.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by yuankui on 1/8/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class ReqResultMap<T> implements Result {
    private final boolean success;
    private final Map<T, String> result;
    private final String message;

    @JsonCreator
    public ReqResultMap(
            @JsonProperty("message") String message,
            @JsonProperty("success") boolean success,
            @JsonProperty("result") Map<T, String> result
    ) {
        this.message = message;
        this.success = success;
        this.result = result;
    }

    ReqResultMap(boolean success, Map<T, String> result, String messageTemplate, Object... params) {
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

    public Map<T, String> getResult() {
        return result;
    }

    public SimpleReqResult asSimple() {
        return SimpleReqResult.create(success, message);
    }

    /* factory methods */

    public static <T> ReqResultMap<T> create(boolean success, Map<T, String> result, String messageTemplate, Object... params) {
        return new ReqResultMap<>(success, result, messageTemplate, params);
    }

    public static <T> ReqResultMap<T> success(Map<T, String> result, String messageTemplate, Object... params) {
        return new ReqResultMap<>(true, result, messageTemplate, params);
    }

    public static <T> ReqResultMap<T> fail(String messageTemplate, Object... params) {
        return new ReqResultMap<>(false, null, messageTemplate, params);
    }

    private static ReqResultMap emptySuccessInstance = success(null, null);

    @SuppressWarnings("unchecked")
    public static <T> ReqResultMap<T> emptySuccess() {
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
