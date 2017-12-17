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
public enum ContractStatus implements IntEnum<ContractStatus> {

    UNSIGNED(1, "has not signed"),
    PENDINGRENTAL(2, "Market cultivation period, did not begin to return rent"),
    RENTAL(3, "has already begun to return rent"),
    NORMALEND(4, "the contract ended normally"),
    ABNORMALEND(5, "the contract ended abnormally");


    private int value;
    private String description;

    ContractStatus(int value, String description) {
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

    private static Map<Integer, ContractStatus> map;
    private static Map<String, ContractStatus> strMap;
    static {
        map = new HashMap<>();
        strMap = new HashMap<>();
        for (ContractStatus t: ContractStatus.values()) {
            map.put(t.getValue(), t);
            strMap.put(t.name(), t);
        }
    }

    public static ContractStatus lookup(int value) {
        return map.get(value);
    }

    public static ContractStatus strLookup(String name) {
        return name == null ? null : strMap.get(name.toUpperCase());
    }
}
