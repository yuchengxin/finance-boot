package pojo;

import com.gilab.wjj.util.excel.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yuankui on 12/19/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class TestUser extends SuperUser{

        @Excel(name = "年龄", width = 60)
        private Long age;

        @Excel(name = "密码")
        private String password;

        @Excel(name = "余额")
        private Double money;

        @Excel(name = "生日")
        private Date birthday;

        @Excel(name = "锁定")
        private Boolean locked;

        @Excel(name = "金额")
        private BigDecimal db;

    public TestUser(String name, long age, String password, Double money, Date birthday, Boolean locked, BigDecimal db) {
        super(name);
        this.age = age;
        this.password = password;
        this.money = money;
        this.birthday = birthday;
        this.locked = locked;
        this.db = db;
    }

    public TestUser(){}

    @Override
    public String toString() {
        return "TestUser{" +
                "age=" + age +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", birthday=" + birthday +
                ", locked=" + locked +
                ", db=" + db +
                '}';
    }


    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public BigDecimal getDb() {
        return db;
    }

    public void setDb(BigDecimal db) {
        this.db = db;
    }
}
