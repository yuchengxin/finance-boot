package com.gilab.wjj.front.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * Created by tomas on 3/22/16.
 */
public class DownloadUtil {


    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName, InputStream inputStream, String contentType) throws IOException {
        response.reset();
        response.setContentType(contentType);
        String userAgent = request.getHeader("User-Agent");
        boolean isMSIE = userAgent.contains("Trident");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            String e = isMSIE? URLEncoder.encode(fileName, "UTF-8"):new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
//            String e = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + e);
            ServletOutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];

            int bytesRead;
            while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (IOException var17) {
            throw var17;
        } finally {
            if(bis != null) {
                bis.close();
            }

            if(bos != null) {
                bos.close();
            }

        }

    }
}
