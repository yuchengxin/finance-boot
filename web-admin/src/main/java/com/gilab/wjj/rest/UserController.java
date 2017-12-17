package com.gilab.wjj.rest;


import com.gilab.wjj.core.UserAgent;
import com.gilab.wjj.persistence.model.User;
import com.gilab.wjj.util.logback.LoggerFactory;
import com.gilab.wjj.utils.RestUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yuankui on 10/31/17.
 */

@Api(value = "API - UserController", description = "用户API详情")
@RestController
@RequestMapping("/api/admin/v1")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserAgent userMgr;

    @ApiOperation(value = "创建用户", notes = "创建单个用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户实例", required = true, dataType = "User", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/users", method = { RequestMethod.PUT }, produces = "application/json")
    public User createUser(final HttpServletResponse response,
                           @RequestBody User user) throws IOException {
        //TODO
        //登录判断

        final User u = new User.Builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .permissions(user.getPermissions())
                .description(user.getDescription())
                .build();

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, userMgr.createUser(u));
    }


}
