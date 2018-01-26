import com.gilab.wjj.persistence.model.BasicRentInfo;
import com.gilab.wjj.util.excel.ExcelDataFormatter;
import com.gilab.wjj.util.excel.ExcelUtils;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuankui on 1/26/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class ExcelTest {
    @Test
    public void testExcel2Entity() throws Exception {
        List<BasicRentInfo> basicRentInfos = new ExcelUtils<>(new BasicRentInfo()).readFromFile(null, new File("/home/yuankui/tmp/返租明细 - 副本.xlsx"));

        for(BasicRentInfo b : basicRentInfos){
            System.out.println(b);
        }


    }
}
