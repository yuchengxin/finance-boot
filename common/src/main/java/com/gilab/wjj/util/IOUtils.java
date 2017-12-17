package com.gilab.wjj.util;


import com.gilab.wjj.util.logback.LoggerFactory;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * IOUtils
 * Author: yuankui
 * Desc:
 * <p/>
 * Change Log:
 * 2016/7/27 - created by yuankui
 */
public class IOUtils {
    private static final Logger logger = LoggerFactory.getLogger(IOUtils.class);

    public static void closeQuietly(AutoCloseable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (Exception e) {
            /* ignore */
        }
    }

    public static String readResource(ClassLoader loader, String path) {
        URL url = loader.getResource(path);
        if (url == null) return null;
        File file = new File(url.getPath());
        if (file.exists() && file.canRead()) {
            return readFile(file, "\n");
        }
        return null;
    }

    public static String readFile(File file, String lineSeparator) {
        return readFile(file, lineSeparator, Integer.MAX_VALUE - 1);
    }

    public static String readFile(File file, String lineSeparator, int limit) {
        Objects.requireNonNull(file, "Input file is null");
        if (!file.exists() || !file.canRead())
            return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String line;
            int len = 0;
            while ((line = reader.readLine()) != null && (limit > 0 && len < limit)) {
                builder.append(line);
                builder.append(lineSeparator);
            }
            if (limit > 0 && len > limit) {
                builder.append("[Exceeds length limitation(")
                        .append(limit)
                        .append(")]");
            }
            return builder.toString();
        } catch (IOException ioe) {
            logger.warn("Failed to read file " + file.getAbsolutePath(), ioe);
            return null;
        }
    }
}
