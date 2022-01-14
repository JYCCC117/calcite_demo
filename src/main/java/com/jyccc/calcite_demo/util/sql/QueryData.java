package com.jyccc.calcite_demo.util.sql;

import java.util.List;

public class QueryData {

    /**
     * 表名
     */
    private String tableName;

    private List<QueryParam> queryParams;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<QueryParam> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(List<QueryParam> queryParams) {
        this.queryParams = queryParams;
    }
}
