package com.gilab.wjj.front.rest;


import com.gilab.wjj.core.TestAgent;
import com.gilab.wjj.persistence.model.MenuList;
import com.gilab.wjj.persistence.model.User;
import com.gilab.wjj.util.logback.LoggerFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.gilab.wjj.utils.RestUtils;

/**
 * Created by yuankui on 10/31/17.
 */

@Api(value = "API - UserController", description = "用户API详情")
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestAgent testMgr;
    /**
     * 用户登录请求
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/login", method = { RequestMethod.POST }, produces = "application/json")
    public Map<String,Object> login(@RequestBody User user,BindingResult bindingResult,HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        if(user.getUsername()==null || user.getUsername().equals("")){
            map.put("success", false);
            map.put("errorInfo", "请输入账号密码！");
            return map;
        }
        if(user.getUsername().equals("chejian")){
            map.put("success", true);
            session.setAttribute("currentUser", user); // 保存当前角色信息
//            logService.save(new Log(Log.LOGIN_ACTION,"用户登录")); // 写入日志
            return map;
        }else{
            map.put("success", false);
            map.put("errorInfo", "账号密码错误！");
            return map;
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
        User currentUser=(User) session.getAttribute("currentUser");
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


    /**
     * 添加合同记录
     * @param map
     * @return
     */
//    @ResponseBody
    @RequestMapping(value="/addcontract", method = { RequestMethod.POST }, produces = "application/json")
    public Map<String,Object> addContract(@RequestBody HashMap map,HttpSession session){
//        System.out.print(map);
//        String contractNo = (String)map.get("contractNo");
//        String signer = (String)map.get("signer");
//        HashMap<String,String> merchantList = (HashMap<String,String>)testMgr.getMerchantByNameAndBankAccount(map);
//
//        //若没有受益人信息，则添加受益人信息
//        if(merchantList==null || merchantList.size()==0){
//            HashMap<String,String> tmpMap = (HashMap<String,String>)map.clone();
//            tmpMap.remove("merchantName");
//            tmpMap.put("merchantName",tmpMap.get("signerMerchantName"));
//            tmpMap.remove("merchantIdNo");
//            tmpMap.put("merchantIdNo",tmpMap.get("signerMerchantIdNo"));
//            testMgr.addMerchant(map);
//        }

//        merchantList.clear();
//        merchantList = new HashMap<String,String>();
//        merchantList = (HashMap<String,String>)testMgr.getMErchantByName();
//        //若没有业主信息，则新增业主信息
//        if(merchantList==null || merchantList.size()==0){
//            HashMap<String,String> tmpMap = (HashMap<String,String>)map.clone();
//            tmpMap.remove("bankInfo");
//            tmpMap.remove("bankAccount");
//            testMgr.addMerchant(tmpMap);
//        }

        System.out.println(map);
        ArrayList signerJsonString = (ArrayList)map.get("signer");
        JsonObject signerJson = new JsonParser().parse(signerJsonString.get(0).toString()).getAsJsonObject();
        map.put("signer",signerJson.get("merchantName").toString().replaceAll("\"",""));
        Integer resId = testMgr.addContract(map);
        System.out.println("addContract return id is:"+resId);

        Map resMap =new HashMap();
        resMap.put("success", true);
        System.out.println(resMap);
        System.out.println("addContract return2 id is:"+resMap.get("returnid"));
        return resMap;
    }

    /*
    *取最近n条合同数据信息
     */
    @RequestMapping(value="/contractlist", method = { RequestMethod.POST }, produces = "application/json")
    public ArrayList<Map<String,Object>> contractlist(@RequestBody HashMap map,HttpSession session){
//        System.out.print(contract.getPaybackDate()+contract.getSigningDate());
//
//        System.out.print(merchant.getMerchantName()+merchant.getMerchantPhone());
        ArrayList<Map<String,Object>> resMaps = testMgr.getContract(map);
//        Map<String,Object> resmap=new HashMap<String,Object>();
//        resmap.put("success", true);
        System.out.println(resMaps);
        return resMaps;
    }

    /*
    *根据signer签约人查询合同
     */
    @RequestMapping(value="/contractsearch", method = { RequestMethod.POST }, produces = "application/json")
    public ArrayList<Map<String,Object>> contractSearch(@RequestBody HashMap map,HttpSession session){
        ArrayList<Map<String,Object>> resMaps = testMgr.contractSearch(map);
        System.out.println(resMaps);
        return resMaps;
    }

    /*
    *根据merchantName查询商户信息
     */
    @RequestMapping(value="/searchMerchant", method = { RequestMethod.POST }, produces = "application/json")
    public ArrayList<Map<String,Object>> searchMerchant(@RequestBody HashMap map,HttpSession session){
        ArrayList<Map<String,Object>> resMaps = testMgr.searchMerchant(map);
        System.out.println(resMaps);
        return resMaps;
    }
    /*
    *添加商户信息
     */
    @RequestMapping(value="/addMerchant", method = { RequestMethod.POST }, produces = "application/json")
    public Map<String,Object> addMerchant(@RequestBody HashMap map,HttpSession session){
       testMgr.addMerchant(map);
       map.put("success",true);
        return map;
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



}
