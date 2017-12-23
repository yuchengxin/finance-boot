import com.gilab.wjj.util.excel.ExcelDataFormatter;
import com.gilab.wjj.util.excel.ExcelUtils;
import org.junit.Test;
import pojo.TestUser;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;



/**
 * Created by yuankui on 12/19/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class ExcelUtilsTest {

    @Test
    public void testEntity2Execl() throws Exception {
        List<TestUser> list = new ArrayList<>();
        TestUser u1 = new TestUser("张三", 19, "123456", 25.33 ,new Date(), false, new BigDecimal(2344));
        list.add(u1);

        TestUser u2 = new TestUser("李四", 20, "123456", 26.33 ,new Date(), false, new BigDecimal(2444));
        list.add(u2);
        TestUser u3 = new TestUser("王五", 21, "653421", 27.33 ,new Date(), false, new BigDecimal(2544));
        list.add(u3);
        TestUser u4 = new TestUser("展六", 22, "123456", 28.33 ,new Date(), false, new BigDecimal(2644));
        list.add(u4);
        TestUser u5 = new TestUser("黑其", 23, "654321", 29.33 ,new Date(), false, new BigDecimal(2744));
        list.add(u5);

        ExcelDataFormatter edf = new ExcelDataFormatter();
        Map<String, String> map = new HashMap<>();
        map.put("true", "真");
        map.put("false", "假");
        edf.set("locked", map);

        ExcelUtils.writeToFile(list,edf, "/home/yuankui/tmp/test2.xlsx");

    }

    @Test
    public void testExcel2Entity() throws Exception {

        ExcelDataFormatter edf = new ExcelDataFormatter();
        Map<String, String> map = new HashMap<>();
        map.put("真", "true");
        map.put("假", "false");
        edf.set("locked", map);

        List<TestUser> users = new ExcelUtils<>(new TestUser()).readFromFile(edf, new File("/home/yuankui/tmp/test2.xlsx"));

        for(TestUser u : users){
            System.out.println(u.getName()+ " : "+u);
        }


    }


}

