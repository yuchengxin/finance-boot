package com.gilab.wjj.persistence.handler;


import com.gilab.wjj.util.DateUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

/**
 * Created by yuankui on 10/31/17.
 */
public class TimestampStringHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, new Timestamp(DateUtils.parseDate(parameter)));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName);
        if (rs.wasNull())
            return null;
        return DateUtils.datetimeString(ts.getTime());
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex);
        if (rs.wasNull())
            return null;
        return DateUtils.datetimeString(ts.getTime());
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp ts = cs.getTimestamp(columnIndex);
        if (cs.wasNull())
            return null;
        return DateUtils.datetimeString(ts.getTime());
    }
}
