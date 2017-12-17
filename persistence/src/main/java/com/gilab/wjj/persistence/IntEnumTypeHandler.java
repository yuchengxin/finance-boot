package com.gilab.wjj.persistence;

import com.gilab.wjj.persistence.model.IntEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuankui on 10/31/17.
 */
public abstract class IntEnumTypeHandler<T extends Enum<T> & IntEnum<T>> extends BaseTypeHandler<T> {
    private final Map<Integer, T> map;

    public IntEnumTypeHandler(Class<T> type) {
        map = new HashMap<>();
        for (T enumValue: type.getEnumConstants()) {
            map.put(enumValue.getValue(), enumValue);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i,
                                    T enumValue, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, enumValue.getValue());
    }

    @Override
    public T getNullableResult(ResultSet rs, String s) throws SQLException {
        int r = rs.getInt(s);
        if (rs.wasNull())
            return null;
        return map.get(r);
    }

    @Override
    public T getNullableResult(ResultSet rs, int i) throws SQLException {
        int r = rs.getInt(i);
        if (rs.wasNull())
            return null;
        return map.get(r);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int i) throws SQLException {
        int r = cs.getInt(i);
        if (cs.wasNull())
            return null;
        return map.get(r);
    }
}
