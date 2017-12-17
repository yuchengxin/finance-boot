package com.gilab.wjj.util;

import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;
import net.sf.json.JSONObject;

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

    public static String jsonFromMap(Map<String, String> map) {
        return ConfigFactory.parseMap(map).root().render(ConfigRenderOptions.concise());
    }

    public static Map<String, Object> stringToMap(String inputStr) {
        JSONObject jb = JSONObject.fromObject(inputStr);
        return (Map<String, Object>) jb;
    }
}
