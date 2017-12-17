package com.gilab.wjj.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * SeDerUtils
 * Author: yuankui
 * Desc:
 * Change Log:
 * 2017/10/31 - created by yuankui
 */

public class SeDerUtils {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static ObjectMapper mapper() {
        return mapper;
    }
}
