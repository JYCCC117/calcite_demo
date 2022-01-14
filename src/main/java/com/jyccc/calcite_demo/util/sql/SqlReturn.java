package com.anjiplus.template.gaea.business.util.sql;

import lombok.Data;

import java.util.List;

@Data
public class SqlReturn {
    /**
     * sql语句
     */
    private String sql;

    /**
     * 因为权限增加的字段
     */
    private List<String> paramList;

}
