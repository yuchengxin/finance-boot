import com.gilab.wjj.util.DateUtils;
import org.junit.Test;

import java.util.Calendar;

/**
 * Created by yuankui on 1/23/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class DateUtilsTest {

    @Test
    public void testCalender(){
        Calendar calendar =Calendar.getInstance();
        System.out.println(DateUtils.getDaysOfMonth(calendar.getTimeInMillis()));
        System.out.println(DateUtils.datetimeString(DateUtils.getFirstDayOfMonth(calendar.getTimeInMillis())));
        System.out.println(DateUtils.datetimeString(DateUtils.getLastDayOfMonth(calendar.getTimeInMillis())));
        System.out.println(calendar.get(Calendar.DATE));
    }
}
