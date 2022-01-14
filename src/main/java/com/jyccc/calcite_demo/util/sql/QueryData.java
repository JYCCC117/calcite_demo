package com.anjiplus.template.gaea.business.util.sql;

import lombok.Data;

import java.util.List;

@Data
public class QueryData {

    /**
     * 表名
     */
    private String tableName;

    private List<QueryParam> queryParams;
}
