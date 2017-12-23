package com.gilab.wjj.persistence.handler;

import com.gilab.wjj.persistence.model.Merchant;
import com.gilab.wjj.util.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

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
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Merchant parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, merchant2String(parameter));
    }

    @Override
    public Merchant getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String r = rs.getString(columnName);
        if (rs.wasNull())
            return null;
        return string2Merchant(r);
    }

    @Override
    public Merchant getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String r = rs.getString(columnIndex);
        if (rs.wasNull())
            return null;
        return string2Merchant(r);
    }

    @Override
    public Merchant getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String r = cs.getString(columnIndex);
        if (cs.wasNull())
            return null;
        return string2Merchant(r);
    }

    private String merchant2String(Merchant merchant){
        return merchant.toString();
    }

    private Merchant string2Merchant(String str){
        return (Merchant) StringUtils.string2Entity(str, Merchant.class);
    }


}
