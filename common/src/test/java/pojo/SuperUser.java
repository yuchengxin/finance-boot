package pojo;

import com.gilab.wjj.util.excel.Excel;

/**
 * Created by yuankui on 12/19/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class SuperUser {
    @Excel(name = "姓名", width = 30)
    private String name;

    public SuperUser(String name) {
        this.name = name;
    }

    public SuperUser(){}

    @Override
    public String toString() {
        return "SuperUser{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
