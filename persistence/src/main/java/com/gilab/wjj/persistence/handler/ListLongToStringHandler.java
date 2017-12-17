package com.gilab.wjj.persistence.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class ListLongToStringHandler extends BaseTypeHandler<List<Long>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, list2string(parameter));
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String r = rs.getString(columnName);
        if (rs.wasNull())
            return null;
        return string2list(r);
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String r = rs.getString(columnIndex);
        if (rs.wasNull())
            return null;
        return string2list(r);
    }

    @Override
    public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String r = cs.getString(columnIndex);
        if (cs.wasNull())
            return null;
        return string2list(r);
    }

    private String list2string(List<Long> list) {
        if (list == null || list.isEmpty())
            return null;
        StringBuilder sb = new StringBuilder();
        for (long elem: list) {
            sb.append(elem);
            sb.append(",");
        }
        return sb.substring(0, sb.length()-1);
    }

    private static List<Long> string2list(String str) {
        if (str.length() == 0)
            return null;
        String[] ss = str.split(",");
        List<Long> list = new ArrayList<>();
        for (String s: ss) {
            list.add(Long.valueOf(s));
        }
        return list;
    }
}
