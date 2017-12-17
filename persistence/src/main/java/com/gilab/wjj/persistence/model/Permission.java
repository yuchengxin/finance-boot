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
public enum Permission implements IntEnum<Permission> {
    ADMIN(1),
    READ(2),
    WRITE(3),
    DELETE(4);

    private int value;

    Permission(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    private static Map<Integer, Permission> map;

    static {
        map = new HashMap<>();
        for (Permission s: Permission.values()) {
            map.put(s.getValue(), s);
        }
    }

    public static Permission lookup(int value) {
        return map.get(value);
    }
}
