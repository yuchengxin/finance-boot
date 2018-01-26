package com.gilab.wjj.persistence.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilab.wjj.persistence.mapper.MerchantMapper;
import com.gilab.wjj.persistence.model.Merchant;
import com.gilab.wjj.util.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class MerchantHandler extends BaseTypeHandler<Merchant> {

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
    public void setNonNullParameter(PreparedStatement ps, int i, Merchant parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, merchant2Long(parameter));
    }

    @Override
    public Merchant getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String r = rs.getString(columnName);
        if (rs.wasNull())
            return null;
        return long2Merchant(r);
    }

    @Override
    public Merchant getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String r = rs.getString(columnIndex);
        if (rs.wasNull())
            return null;
        return long2Merchant(r);
    }

    @Override
    public Merchant getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String r = cs.getString(columnIndex);
        if (cs.wasNull())
            return null;
        return long2Merchant(r);
    }

    private long merchant2Long(Merchant merchant){
        return merchant.getId();
    }

    private Merchant long2Merchant(String str){
        return getMerchant(Integer.valueOf(str));
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
