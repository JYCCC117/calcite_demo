package com.anjiplus.template.gaea.business.util.sql;

import lombok.Data;

import java.util.List;

@Data
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

}
