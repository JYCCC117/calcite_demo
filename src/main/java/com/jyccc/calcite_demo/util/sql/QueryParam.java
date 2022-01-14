package com.jyccc.calcite_demo.util.sql;


import java.util.List;

public class QueryParam {
    /**
     * 字段名
     */
    private String paramName;

    /**
     * 别名
     */
    private String aliasName;

    /**
     * 数据
     */
    private List<String> dataList;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }
}
