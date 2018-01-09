package com.gilab.wjj.front.rest;

import com.gilab.wjj.core.UserAgent;
import com.gilab.wjj.front.utils.RestUtils;
import com.gilab.wjj.persistence.model.Merchant;
import com.gilab.wjj.persistence.model.SimpleReqResult;
import com.gilab.wjj.persistence.model.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chejian on 12/23/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Api(value = "API - UserController", description = "用户API详情")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserAgent userMgr;

    @ApiOperation(value = "添加用户", notes = "创建单个用户", produces = "application/json")
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
                                   @RequestBody final User user) throws IOException {
        return RestUtils.getOrSendError(response, userMgr.createUser(user));
    }

    @ApiOperation(value = "删除用户", notes = "根据ID删除单个用户", produces = "application/json")
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
    @RequestMapping(value = "/deleteUser", method = { RequestMethod.PUT }, produces = "application/json")
    public Map deleteUser(final HttpServletResponse response,
                           @RequestParam(name = "userId", required = true) final long userId) throws IOException {
        userMgr.deleteUser(userId);
        Map res = new HashMap();
        res.put("SUCCESS",true);
        return res;
    }

    @ApiOperation(value = "查询用户", notes = "根据id查询单个用户", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/users{userId}", method = { RequestMethod.GET }, produces = "application/json")
    public User getUser(final HttpServletResponse response,
                                   @PathVariable final long userId) throws IOException {
        //TODO
        //登录判断

        //TODO
        //权限判断
        return RestUtils.getOrSendError(response, userMgr.getUser(userId));
    }

    @ApiOperation(value = "查询用户", notes = "根据用户名", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户姓名", dataType = "String", paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/users", method = { RequestMethod.GET }, produces = "application/json")
    public List<User> getUserWithFilter(final HttpServletResponse response,
                                                @RequestParam(name = "username", required = false) final String username) throws IOException {
        return userMgr.getUserWithFilter(username);
    }



    @ApiOperation(value = "修改用户资料", notes = "修改用户资料", produces = "application/json")
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
    @RequestMapping(value = "/updateUser", method = { RequestMethod.POST }, produces = "application/json")
    public SimpleReqResult updateUser(final HttpServletResponse response,
                              @RequestBody final User user,HttpSession session) throws IOException {
        return userMgr.updateUser(user);
    }


    @ApiOperation(value = "修改密码", notes = "修改登陆密码", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "permission", value = "权限名", dataType = "String", paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/modifyPassword", method = { RequestMethod.POST }, produces = "application/json")
    public Map modifyPassword(final HttpServletResponse response,
                              @RequestBody final User user,HttpSession session) throws IOException {
        User currentUser = (User) session.getAttribute("currentUser");
        currentUser.setPassword(user.getPassword());
        userMgr.modifyPassword(currentUser);
        Map res = new HashMap();
        res.put("SUCCESS",true);
        return res;
    }

}
