package com.gilab.wjj.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;
import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * StringUtils
 * Author: yuankui
 * Desc:
 * <p/>
 * Change Log:
 * 2016/7/8 - created by yuankui
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String nullIfEmpty(String str) {
        if (str != null && str.isEmpty())
            return null;
        return str;
    }

    public static String join(Collection<?> collection, String delimiter) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Object obj : collection) {
            if (first) {
                sb.append(obj);
                first = false;
            } else {
                sb.append(delimiter).append(obj);
            }
        }
        return sb.toString();
    }

    public static boolean equalsWithNull(String str1, String str2) {
        if (isEmpty(str1)) {
            return isEmpty(str2);
        }
        return str2 != null && str1.equals(str2);
    }


    public static List<String> partitionCommandLine(String command) {
        List<String> tokens = new ArrayList<>();
        int index = 0;
        StringBuilder builder = new StringBuilder(command.length());
        boolean isApos = false;
        boolean isQuote = false;
        while (index < command.length()) {
            char c = command.charAt(index);
            switch (c) {
                case ' ':
                    if (!isQuote && !isApos) {
                        String arg = builder.toString();
                        builder = new StringBuilder(command.length() - index);
                        if (arg.length() > 0) {
                            tokens.add(arg);
                        }
                    } else {
                        builder.append(c);
                    }
                    break;
                case '\'':
                    if (!isQuote) {
                        isApos = !isApos;
                    } else {
                        builder.append(c);
                    }
                    break;
                case '"':
                    if (!isApos) {
                        isQuote = !isQuote;
                    } else {
                        builder.append(c);
                    }
                    break;
                default:
                    builder.append(c);
            }
            index++;
        }

        if (builder.length() > 0) {
            String arg = builder.toString();
            tokens.add(arg);
        }
        return tokens;
    }

    public static String map2String(Map<String, String> map) {
        return ConfigFactory.parseMap(map).root().render(ConfigRenderOptions.concise());
    }

    public static Map<String, Object> string2Map(String inputStr) {
        JSONObject jb = JSONObject.fromObject(inputStr);
        return (Map<String, Object>) jb;
    }

    public static <T> T string2Entity(String str, Class<T> entity){
        ObjectMapper om = new ObjectMapper();
        T readValue = null;
        try {
            readValue = om.readValue(str, entity);
        } catch (JsonParseException | IOException e) {
            e.printStackTrace();
        }
        return readValue;
    }

    public static <T> String Entity2String(T t){
        ObjectMapper om = new ObjectMapper();
        try {
            String json = om.writeValueAsString(t);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
