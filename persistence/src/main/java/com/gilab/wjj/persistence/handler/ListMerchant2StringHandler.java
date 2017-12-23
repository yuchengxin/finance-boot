package com.gilab.wjj.persistence.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilab.wjj.persistence.mapper.MerchantMapper;
import com.gilab.wjj.persistence.model.Merchant;
import com.gilab.wjj.persistence.model.Permission;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class ListMerchant2StringHandler extends BaseTypeHandler<List<Merchant>> {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static ThreadLocal<SqlSession> trans ;

    private static SqlSessionFactory factory;

    static {
        try {
            trans = new ThreadLocal<>();
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("config/mybatis-config.xml"), null, null);
        } catch (IOException e) {
            factory = null;
            e.printStackTrace();
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Merchant> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, list2string(parameter));
    }

    @Override
    public List<Merchant> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String r = rs.getString(columnName);
        if (rs.wasNull())
            return null;
        return string2list(r);
    }

    @Override
    public List<Merchant> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String r = rs.getString(columnIndex);
        if (rs.wasNull())
            return null;
        return string2list(r);
    }

    @Override
    public List<Merchant> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String r = cs.getString(columnIndex);
        if (cs.wasNull())
            return null;
        return string2list(r);
    }

    private String list2string(List<Merchant> list) {
        if (list == null || list.isEmpty())
            return null;
        StringBuilder sb = new StringBuilder();
        for (Merchant elem: list) {
            sb.append(elem.getId());
            sb.append(",");
        }
        return sb.substring(0, sb.length()-1);
    }

    private List<Merchant> string2list(String str) {
        if (str.length() == 0)
            return null;
        String[] ss = str.split(",");
        List<Merchant> list = new ArrayList<>();
        for (String s: ss) {
            list.add(getMerchant(Integer.valueOf(s)));
        }
        return list;
    }

    private Merchant getMerchant(long merchantId) {
        SqlSession trx = trans.get();
        if (trx == null) {
            try (SqlSession newSess = factory.openSession()) {
                return merchantMapper(newSess)
                        .selectMerchant(merchantId);
            }
        } else {
            return merchantMapper(trx)
                    .selectMerchant(merchantId);
        }
    }

    private MerchantMapper merchantMapper(SqlSession session) {
        return session.getMapper(MerchantMapper.class);
    }
}
