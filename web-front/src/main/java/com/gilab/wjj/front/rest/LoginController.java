package com.gilab.wjj.front.rest;

import com.gilab.wjj.core.LoginAgent;
import com.gilab.wjj.core.UserAgent;
import com.gilab.wjj.persistence.model.MenuList;
import com.gilab.wjj.persistence.model.PermissionList;
import com.gilab.wjj.persistence.model.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 菜单请求
     * @param parentId
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value="/menu", method = { RequestMethod.POST }, produces = "application/json")
    @RequestMapping(value="/menu", method = { RequestMethod.POST }, produces={"text/html;charset=UTF-8;","application/json;"})
    public  String menu(@RequestParam("parentId") Integer parentId,HttpSession session){
        JsonArray jsonArray = getAllMenuByParentId(parentId);
        ArrayList<MenuList> menuLists = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); i++){
            MenuList menuList = new MenuList();
            menuList.setId(jsonArray.get(i).getAsJsonObject().get("id").getAsInt());
            menuList.setName(jsonArray.get(i).getAsJsonObject().get("text").getAsString());
            menuLists.add(menuList);
        }
        ArrayList<JsonObject> res = new ArrayList<>();
        res.add(jsonArray.get(0).getAsJsonObject());
//        return jsonArray.toString();
        return jsonArray.toString();
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
        List<MenuList> menuList =loginMgr.getMenuByParentId(parentId);
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
