package com.gilab.wjj.front.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilab.wjj.persistence.ext.AjaxErrorMessage;
import com.gilab.wjj.persistence.model.ReqResult;
import com.gilab.wjj.persistence.model.ReqResultMap;
import com.gilab.wjj.persistence.model.SimpleReqResult;
import com.gilab.wjj.util.logback.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by jiangzhe on 17-3-3.
 */
public class RestUtils {
    private static final Logger logger = LoggerFactory.getLogger(RestUtils.class);

    private static final ObjectMapper mapper = new ObjectMapper();
    private static String authcFailJson;
    private static String authcMissJson;
    static {
        {
            AjaxErrorMessage msg = new AjaxErrorMessage(70013, "登陆失败");
            try {
                authcFailJson = mapper.writeValueAsString(msg);
            } catch (JsonProcessingException jpe) {
                logger.error("Failed to generate default error message using jackson", jpe);
            }
        }
        {
            AjaxErrorMessage msg = new AjaxErrorMessage(70013, "用户未登陆");
            try {
                authcMissJson = mapper.writeValueAsString(msg);
            } catch (JsonProcessingException jpe) {
                logger.error("Failed to generate default error message using jackson", jpe);
            }
        }
    }

    public static <T> T sendAuthcMiss(HttpServletResponse response) throws IOException {
        return sendErrorJson(response, HttpServletResponse.SC_UNAUTHORIZED, authcMissJson);
    }

    public static <T> T sendAuthcFail(HttpServletResponse response) throws IOException {
        return sendErrorJson(response, HttpServletResponse.SC_UNAUTHORIZED, authcFailJson);
    }

    public static <T> T sendErrorJson(HttpServletResponse response, int code, String message) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(code);
        // add CORS support, should be commented in production
        addCorsHeader(response);
        PrintWriter writer = response.getWriter();
        writer.write(message);
        writer.close();
        return null;
    }

    public static <T> T sendError(HttpServletResponse response, int code, Object message) throws IOException {
        String json = mapper.writeValueAsString(message);
        return sendErrorJson(response, code, json);
    }

    public static <T> T getOrSendError(HttpServletResponse response, ReqResult<T> attempt, int code) throws IOException {
        if (attempt.isSuccess()) return attempt.getResult();
        sendError(response, code, new AjaxErrorMessage(code, attempt.getMessage()));
        return null;
    }

    public static <T> T getOrSendError(HttpServletResponse response, ReqResult<T> attempt) throws IOException {
        return getOrSendError(response, attempt, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public static SimpleReqResult getOrSendError(HttpServletResponse response, SimpleReqResult attempt, int code) throws IOException {
        if(attempt.isSuccess()) return attempt;
        sendError(response, code, new AjaxErrorMessage(code, attempt.getMessage()));
        return null;
    }

    public static SimpleReqResult getOrSendError(HttpServletResponse response, SimpleReqResult attempt) throws IOException {
        return getOrSendError(response, attempt, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public static <T> Map<T, String> getOrSendError(HttpServletResponse response, ReqResultMap<T> attempt, int code) throws IOException {
        if (attempt.isSuccess()) return attempt.getResult();
        sendError(response, code, new AjaxErrorMessage(code, attempt.getMessage()));
        return null;
    }

    public static <T> Map<T, String> getOrSendError(HttpServletResponse response, ReqResultMap<T> attempt) throws IOException {
        return getOrSendError(response, attempt, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    private static void addCorsHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
    }

}
