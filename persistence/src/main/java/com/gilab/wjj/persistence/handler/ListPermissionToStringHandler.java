package com.gilab.wjj.persistence.handler;

import com.gilab.wjj.persistence.model.Permission;
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
public class ListPermissionToStringHandler extends BaseTypeHandler<List<Permission>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Permission> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, list2string(parameter));
    }

    @Override
    public List<Permission> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String r = rs.getString(columnName);
        if (rs.wasNull())
            return null;
        return string2list(r);
    }

    @Override
    public List<Permission> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String r = rs.getString(columnIndex);
        if (rs.wasNull())
            return null;
        return string2list(r);
    }

    @Override
    public List<Permission> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String r = cs.getString(columnIndex);
        if (cs.wasNull())
            return null;
        return string2list(r);
    }

    private String list2string(List<Permission> list) {
        if (list == null || list.isEmpty())
            return null;
        StringBuilder sb = new StringBuilder();
        for (Permission elem: list) {
            sb.append(elem.getValue());
            sb.append(",");
        }
        return sb.substring(0, sb.length()-1);
    }

    private static List<Permission> string2list(String str) {
        if (str.length() == 0)
            return null;
        String[] ss = str.split(",");
        List<Permission> list = new ArrayList<>();
        for (String s: ss) {
            list.add(Permission.lookup(Integer.valueOf(s)));
        }
        return list;
    }
}
