package com.gilab.wjj.persistence.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gilab.wjj.persistence.model.PeriodCalStandard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.poi.ss.formula.functions.T;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuankui on 12/22/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class ListPeriodCalStandard2StringHandler extends BaseTypeHandler<List<PeriodCalStandard>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<PeriodCalStandard> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, list2string(parameter));
    }

    @Override
    public List<PeriodCalStandard> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String r = rs.getString(columnName);
        if (rs.wasNull())
            return null;
        return string2list(r);
    }

    @Override
    public List<PeriodCalStandard> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String r = rs.getString(columnIndex);
        if (rs.wasNull())
            return null;
        return string2list(r);
    }

    @Override
    public List<PeriodCalStandard> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String r = cs.getString(columnIndex);
        if (cs.wasNull())
            return null;
        return string2list(r);
    }

    private String list2string(List<PeriodCalStandard> list) {
        if (list == null || list.isEmpty())
            return null;
        return JSON.toJSON(list).toString();
    }

    private static List<PeriodCalStandard> string2list(String str) {
        if (str.length() == 0)
            return null;
        return JSONObject.parseArray(str, PeriodCalStandard.class);
    }
}
