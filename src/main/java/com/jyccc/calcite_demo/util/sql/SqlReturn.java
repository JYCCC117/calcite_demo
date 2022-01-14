package com.jyccc.calcite_demo.util.sql;


import java.util.List;

public class SqlReturn {
    /**
     * sql语句
     */
    private String sql;

    /**
     * 因为权限增加的字段
     */
    private List<String> paramList;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<String> getParamList() {
        return paramList;
    }

    public void setParamList(List<String> paramList) {
        this.paramList = paramList;
    }
}
