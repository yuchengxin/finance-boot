package com.gilab.wjj.persistence.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuankui on 1/8/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public enum  SigningStatus  implements IntEnum<SigningStatus> {

    UNSIGNED(1, "未签"),
    SIGNED(2, "已签");

    private int value;
    private String description;

    SigningStatus(int value, String description) {
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

    private static Map<Integer, SigningStatus> map;
    private static Map<String, SigningStatus> strMap;
    private static Map<String, SigningStatus> desMap;
    static {
        map = new HashMap<>();
        strMap = new HashMap<>();
        desMap = new HashMap<>();
        for (SigningStatus t: SigningStatus.values()) {
            map.put(t.getValue(), t);
            strMap.put(t.name(), t);
            desMap.put(t.getDescription(), t);
        }
    }

    public static SigningStatus lookup(int value) {
        return map.get(value);
    }

    public static SigningStatus strLookup(String name) {
        return name == null ? null : strMap.get(name.toUpperCase());
    }

    public static SigningStatus desLookup(String description){
        return description == null ? null : desMap.get(description);
    }
}
