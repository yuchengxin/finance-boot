package com.gilab.wjj.front.rest;

import com.gilab.wjj.core.LoginAgent;
import com.gilab.wjj.core.TestAgent;
import com.gilab.wjj.core.UserAgent;
import com.gilab.wjj.persistence.model.MenuList;
import com.gilab.wjj.persistence.model.PermissionList;
import com.gilab.wjj.persistence.model.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by yuankui on 12/22/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */

@Api(value = "API - ContractController", description = "LoginAPI详情")
@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private LoginAgent loginMgr;

    @Autowired
    private UserAgent userMgr;

    @Autowired
    private TestAgent testMgr;

    static final String TEST_USERNAME = "chejian";
    static final String TEST_PASSWORD = "wjj";
    /**
     * 用户登录请求
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/login", method = { RequestMethod.POST }, produces = "application/json")
    public Map<String,Object> login(@RequestBody User user,BindingResult bindingResult,HttpSession session){
        /*
        ----------用于程序员调试专用，发布前需注释掉----------
         */
        if(user.getUsername().equals(TEST_USERNAME) && user.getPassword().equals(TEST_PASSWORD)){
            session.setAttribute("currentUser", user); // 保存当前角色信息
            TestController testController  =new TestController();
            return testController.login(user,bindingResult,session);
        }
        /*
        ----------用于程序员调试专用，发布前需注释掉----------
         */
        Map<String,Object> map=new HashMap<String,Object>();
        if(user.getUsername()==null || user.getUsername().equals("")){
            map.put("success", false);
            map.put("errorInfo", "请输入账号密码！");
            return map;
        }

        List<User> users = userMgr.getUserWithFilter(user.getUsername());
        for(User tmpUser :users){
            if(tmpUser.getUsername().equals(user.getUsername()) && tmpUser.getPassword().equals(user.getPassword())){
                map.put("success", true);
                session.setAttribute("currentUser", tmpUser); // 保存当前角色信息
                return map;
            }
        }

        map.put("success", false);
        map.put("errorInfo", "账号密码错误！");
        return map;

//        if(user.getUsername().equals("chejian")){
//            map.put("success", true);
//            session.setAttribute("currentUser", user); // 保存当前角色信息
////            logService.save(new Log(Log.LOGIN_ACTION,"用户登录")); // 写入日志
//            return map;
//        }else{
//            map.put("success", false);
//            map.put("errorInfo", "账号密码错误！");
//            return map;
//        }
    }

    /**
     * 加载当前用户信息
     * @param session
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="/loadUserInfo", method = { RequestMethod.GET }, produces={"text/html;charset=UTF-8;","application/json;"})
    public String loadUserInfo(HttpSession session)throws Exception{
        User currentUser=(User) session.getAttribute("currentUser");
        if(currentUser==null || currentUser.getPermissions()==null){
            return "欢迎您: 超级管理员";
        }else {
            String permissionString = currentUser.getPermissions().get(0).toString()=="ADMIN"?"管理员":"操作员";
            return "欢迎您：" + currentUser.getUsername() + "&nbsp;[&nbsp;" + permissionString + "&nbsp;]";
        }
    }


    /**
     * 菜单请求
     * @param parentId
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value="/menu", method = { RequestMethod.POST }, produces = "application/json")
    @RequestMapping(value="/menu", method = { RequestMethod.POST }, produces={"text/html;charset=UTF-8;","application/json;"})
    public  String menu(@RequestParam("parentId") Integer parentId,HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");

        JsonArray jsonArray;
         /*
        ----------用于程序员调试专用，发布前需注释掉----------
         */
        if(currentUser != null && (currentUser.getUsername().equals(TEST_USERNAME) && currentUser.getPassword().equals(TEST_PASSWORD))){
            jsonArray = getAllMenuByParentId(parentId);
        }else if(currentUser == null){
            jsonArray = new JsonArray();
            return null;
        }else{
            Integer permission = currentUser.getPermissions().get(0).getValue();
            jsonArray = getAllMenuByParentId(parentId,permission);

        }
        /*
        ----------用于程序员调试专用，发布前需注释掉----------
         */
//        Integer permission = currentUser.getPermissions().get(0).getValue();
//        JsonArray jsonArray = getAllMenuByParentId(parentId,permission);

//        ArrayList<JsonObject> res = new ArrayList<>();
//        res.add(jsonArray.get(0).getAsJsonObject());

        return jsonArray.toString();
    }

    /**
     * 退出
     * @param session
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="/exit", method = { RequestMethod.GET }, produces={"text/html;charset=UTF-8;","application/json;"})
    public String exit(HttpSession session)throws Exception{
        User currentUser=(User) session.getAttribute("currentUser");
        session.removeAttribute("currentUser");
        return currentUser.getUsername();

    }

    private JsonArray getAllMenuByParentId(Integer parentId,Integer permission){
        JsonArray jsonArray=this.getMenuByParentId(parentId, permission);
        for(int i = 0;i<jsonArray.size();i++){
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            if("open".equals(jsonObject.get("state").getAsString())){
                continue;
            }else{
                jsonObject.add("children",getAllMenuByParentId(jsonObject.get("id").getAsInt(), permission));
            }
        }
        return jsonArray;
    }

    private JsonArray getMenuByParentId(Integer parentId,Integer permission){
        List<MenuList> menuList =loginMgr.getMenuByParentId(parentId, permission);
        JsonArray jsonArray = new JsonArray();
        for(MenuList menu:menuList){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("id", menu.getId()); // 节点id
            jsonObject.addProperty("text", menu.getName()); // 节点名称
            if(menu.getState()==1){
                jsonObject.addProperty("state", "closed"); // 根节点
            }else{
                jsonObject.addProperty("state", "open"); // 叶子节点
            }
            jsonObject.addProperty("iconCls", menu.getIcon());
            JsonObject attributeObject=new JsonObject(); // 扩展属性
            attributeObject.addProperty("url", menu.getUrl()); // 菜单请求地址
            jsonObject.add("attributes", attributeObject);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    private JsonArray getAllMenuByParentId(Integer parentId){
        JsonArray jsonArray=this.getMenuByParentId(parentId);
        for(int i = 0;i<jsonArray.size();i++){
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            if("open".equals(jsonObject.get("state").getAsString())){
                continue;
            }else{
                jsonObject.add("children",getAllMenuByParentId(jsonObject.get("id").getAsInt()));
            }
        }
        return jsonArray;
    }

    private JsonArray getMenuByParentId(Integer parentId){
        List<MenuList> menuList =testMgr.getMenuByParentId(parentId);
        JsonArray jsonArray = new JsonArray();
        for(MenuList menu:menuList){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("id", menu.getId()); // 节点id
            jsonObject.addProperty("text", menu.getName()); // 节点名称
            if(menu.getState()==1){
                jsonObject.addProperty("state", "closed"); // 根节点
            }else{
                jsonObject.addProperty("state", "open"); // 叶子节点
            }
            jsonObject.addProperty("iconCls", menu.getIcon());
            JsonObject attributeObject=new JsonObject(); // 扩展属性
            attributeObject.addProperty("url", menu.getUrl()); // 菜单请求地址
            jsonObject.add("attributes", attributeObject);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }






    @ApiOperation(value = "查询权限列表", notes = "根据权限名", produces = "application/json")
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
    @RequestMapping(value = "/permissions", method = { RequestMethod.GET }, produces = "application/json")
    public  List<PermissionList> getPermissionsWithFilter(final HttpServletResponse response,
                                               @RequestParam(name = "permission", required = false) final String permission) throws IOException {
        List<PermissionList> res = loginMgr.getPermissionsWithFilter(permission);
        return res;
    }

    @ApiOperation(value = "删除权限", notes = "根据权限列表ID", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限列表ID", dataType = "Integer", paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/deletePermitionListById", method = { RequestMethod.GET }, produces = "application/json")
    public Map deletePermitionListById(final HttpServletResponse response,
                                               @RequestParam(name = "id", required = true) final Integer id) throws IOException {
        Integer res = loginMgr.deletePermitionListById(id);

        Map resMap = new HashMap();
        if(res>0){
            resMap.put("SUCCESS",true);
        }else{
            resMap.put("SUCCESS",false);
        }
        return resMap;
    }

    @ApiOperation(value = "获取页面列表", notes = "获取所有二级页面列表", produces = "application/json")
    @ApiImplicitParams({})
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/menuList", method = { RequestMethod.GET }, produces = "application/json")
    public List<Map> menuList(final HttpServletResponse response) throws IOException {
        Integer id = 0;
        List<Map> res = loginMgr.menuList(id);

        return res;
    }

    @ApiOperation(value = "获取页面列表", notes = "获取所有二级页面列表", produces = "application/json")
    @ApiImplicitParams({})
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "用户未授权"),
            @ApiResponse(code = 403, message = "用户被禁止"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ResponseBody
    @RequestMapping(value = "/permissionList", method = { RequestMethod.PUT }, produces = "application/json")
    public Map addPermissionList(final HttpServletResponse response,
                                 @RequestParam(name = "menuid", required = true) final String menuid,
                                 @RequestParam(name = "permission", required = true) final String permission) throws IOException {
        long menuidint = Integer.valueOf(menuid);
        loginMgr.addPermissionList(menuidint,permission);
        Map resMap = new HashMap();
        resMap.put("SUCCESS",true);
        return resMap;
    }




}
