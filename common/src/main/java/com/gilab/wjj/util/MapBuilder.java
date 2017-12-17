package com.gilab.wjj.util;

import java.util.HashMap;
import java.util.Map;

/**
 * MapBuilder
 * Author: yuankui
 * <p>
 * Change log:
 * 2/9/17 - created.
 */
public class MapBuilder<T, U> {
    private Map<T, U> map;

    public MapBuilder() {
        map = new HashMap<>();
    }

    public MapBuilder<T, U> withKV(T key, U value) {
        if (key != null && value != null) map.put(key, value);
        return this;
    }

    public Map<T, U> build() {
        return map;
    }

    public static <T, U> Map<T, U> singleKV(T key, U value) {
        Map<T, U> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
