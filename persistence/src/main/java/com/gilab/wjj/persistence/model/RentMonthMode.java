package com.gilab.wjj.persistence.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuankui on 1/26/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public enum RentMonthMode implements IntEnum<RentMonthMode> {
    FIRSTMONTH(1, "第一个月"),
    NORMALMONTH(2, "正常月份"),
    EXTENDMONTH(3, "跨月月份"),
    LASTMONTH(4, "最后一个月");

    private int value;
    private String description;

    RentMonthMode(int value, String description) {
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

    private static Map<Integer, RentMonthMode> map;
    private static Map<String, RentMonthMode> strMap;
    private static Map<String, RentMonthMode> desMap;
    static {
        map = new HashMap<>();
        strMap = new HashMap<>();
        desMap = new HashMap<>();
        for (RentMonthMode t: RentMonthMode.values()) {
            map.put(t.getValue(), t);
            strMap.put(t.name(), t);
            desMap.put(t.getDescription(), t);
        }
    }

    public static RentMonthMode lookup(int value) {
        return map.get(value);
    }

    public static RentMonthMode strLookup(String name) {
        return name == null ? null : strMap.get(name.toUpperCase());
    }

    public static RentMonthMode desLookup(String description){
        return description == null ? null : desMap.get(description);
    }
}
