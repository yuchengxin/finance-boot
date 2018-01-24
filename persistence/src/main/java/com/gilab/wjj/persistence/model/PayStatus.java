package com.gilab.wjj.persistence.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuankui on 1/23/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public enum PayStatus implements IntEnum<PayStatus> {

    UNPAID(1, "未支付"),
    SUCCESSFULPAID(2, "支付成功"),
    FAILEDPAID(3, "支付失敗");


    private int value;
    private String description;

    PayStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    private static Map<Integer, PayStatus> map;
    private static Map<String, PayStatus> strMap;
    static {
        map = new HashMap<>();
        strMap = new HashMap<>();
        for (PayStatus t: PayStatus.values()) {
            map.put(t.getValue(), t);
            strMap.put(t.name(), t);
        }
    }

    public static PayStatus lookup(int value) {
        return map.get(value);
    }

    public static PayStatus strLookup(String name) {
        return name == null ? null : strMap.get(name.toUpperCase());
    }
}
