package com.gilab.wjj.persistence.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public enum SigningMode implements IntEnum<SigningMode> {
    MORTGAGE(1, "按揭贷款"),
    DISPOSABLE(2, "一次性");


    private int value;
    private String description;

    SigningMode(int value, String description) {
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

    private static Map<Integer, SigningMode> map;
    private static Map<String, SigningMode> strMap;
    static {
        map = new HashMap<>();
        strMap = new HashMap<>();
        for (SigningMode t: SigningMode.values()) {
            map.put(t.getValue(), t);
            strMap.put(t.name(), t);
        }
    }

    public static SigningMode lookup(int value) {
        return map.get(value);
    }

    public static SigningMode strLookup(String name) {
        return name == null ? null : strMap.get(name.toUpperCase());
    }
}
