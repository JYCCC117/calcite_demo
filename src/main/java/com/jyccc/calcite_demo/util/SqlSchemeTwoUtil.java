package com.anjiplus.template.gaea.business.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.anjiplus.template.gaea.business.util.calcite.SqlCharStringLiteral2;
import com.anjiplus.template.gaea.business.util.sql.QueryData;
import com.anjiplus.template.gaea.business.util.sql.QueryParam;
import com.anjiplus.template.gaea.business.util.sql.SqlReturn;
import lombok.extern.slf4j.Slf4j;
import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.SqlParserPos;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.util.NlsString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.calcite.sql.SqlKind.*;

@Slf4j
public class SqlSchemeTwoUtil {

    public SqlSchemeTwoUtil() {
    }

    public SqlSchemeTwoUtil(List<QueryData> queryDataList) {
        this.queryDataList = queryDataList;
    }

//    @Data
//    public static class QueryData {
//
//        private String tableName;
//
//        private List<QueryParam> queryParams;
//
//        @Data
//        public static class QueryParam {
//
//            private String paramName;
//
//            private String aliasName;
//
//            private List<String> dataList;
//        }
//    }

    private List<QueryData> queryDataList;

    private static final SqlParserPos ZERO = new SqlParserPos(0, 0);

    private static final SqlBinaryOperator sqlAndOperator = new SqlBinaryOperator("AND", AND, 1, true, null, null, null);

    private static final SqlBinaryOperator sqlInOperator = new SqlBinaryOperator("IN", IN, 1, true, null, null, null);

    private static final SqlAsOperator sqlAsOperator = new SqlAsOperator();

    private static final SqlParser.Config config = SqlParser.configBuilder().setLex(Lex.MYSQL).setCaseSensitive(true).setConformance(SqlConformanceEnum.MYSQL_5).build();

    public List<QueryData> getQueryDataList() {
        return queryDataList;
    }

    public void setQueryDataList(List<QueryData> queryDataList) {
        this.queryDataList = queryDataList;
    }

    /**
     * 匹配获取需要添加的表字段
     *
     * @param tableName 表名
     * @return 结果
     */
    private List<QueryParam> getQueryParamByTableName(String tableName) {
        for (QueryData queryData : queryDataList) {
            if (queryData.getTableName().equalsIgnoreCase(tableName)) {
                return queryData.getQueryParams();
            }
        }
        return new ArrayList<>();
    }


//        private static SqlParser.Config config = SqlParser.configBuilder().setCaseSensitive(true).build();
    //.setConformance(SqlConformanceEnum.MYSQL_5)  limit 10,10
    //.setCaseSensitive(true)大小写敏感
//        private static SqlParser.Config DEFAULT = (ImmutableBeans.create(SqlParser.Config.class)).withLex(Lex.MYSQL).withIdentifierMaxLength(128).withConformance(SqlConformanceEnum.DEFAULT).withParserFactory(SqlParserImpl.FACTORY);
    public static void main(String[] args) throws Exception {
//        String sql = "SELECT sum(x.dd) as xx, (select * from a) as ii,2 from db.a x where id = xx and c = 'zz' union all SELECT sum(f) as xx,e FROM db.B left join B.dd on dd.xx=b.cc WHERE g = h limit 10,10";
//        String sql = "SELECT a.*, (select id from aj_report_table where id = 8) as xx FROM aj_report_table a LEFT JOIN aj_report_table b ON a.id = b.id  WHERE a.id > 1";
//        String sql = "SELECT a.id, b.id FROM aj_report_table a LEFT JOIN aj_report_nums b ON a.id = b.id LEFT JOIN aj_report_nums c ON a.id = c.id WHERE a.id > 1";
        String sql = "SELECT a.id, b.id FROM aj_report_table a LEFT JOIN (select * from aj_report_nums) b ON a.id = b.id LEFT JOIN aj_report_nums c ON a.id = c.id WHERE a.id > 1 or b.id > 1";
//        String sql = "SELECT a.id, b.id FROM aj_report_table a LEFT JOIN ( SELECT * FROM (select * from aj_report_nums e) d ) b ON a.id = b.id LEFT JOIN (SELECT * from aj_report_nums) c ON a.id = c.id  WHERE a.id > 1 OR b.id > 1";
//        String sql = "SELECT a.id, b.id FROM (select * from aj_report_table) a INNER JOIN aj_report_nums b ON a.id = b.id WHERE a.id > 1";
//        String sql = "SELECT a.id, b.id FROM (select c.* from aj_report_table c, aj_report_nums) a LEFT JOIN aj_report_nums b ON a.id = b.id WHERE a.id > 1";
//        String sql = "SELECT * FROM aj_report_table a where a.id > 1";
//        String sql = "SELECT * FROM `energy_data` WHERE `ID` = 579224";
//        String sql = "SELECT * FROM (SELECT * from aj_report_table) a where a.id > 1";
//        String sql = "SELECT id  FROM ( SELECT id FROM ( SELECT id FROM aj_report_table ) s2 ) AS s1";
//        String sql = "INSERT INTO `aj_report_init`.`aj_report_access` (`datetime`, `access`, `register`) VALUES ('2021-06-18', 1000, 12)";
//        String sql = "ALTER TABLE 'public'.'report_data_source' ADD COLUMN 'create_id' int8, ADD COLUMN 'update_id' int8;";

        List<QueryData> queryDataList = new ArrayList<>();
        QueryData queryData = new QueryData();
        queryData.setTableName("aj_report_table");
        List<QueryParam> queryParams = new ArrayList<>();
        QueryParam queryParam = new QueryParam();
        queryParam.setParamName("id");
        queryParam.setAliasName(queryData.getTableName() + UuidUtil.generateUuid());
        List<String> dataList = new ArrayList<>();
        dataList.add("177");
        dataList.add("9");
        dataList.add("10");
        queryParam.setDataList(dataList);
        queryParams.add(queryParam);
        queryData.setQueryParams(queryParams);
        queryDataList.add(queryData);


        QueryData queryData1 = new QueryData();
        queryData1.setTableName("aj_report_nums");
        List<QueryParam> queryParams1 = new ArrayList<>();
        QueryParam queryParam1 = new QueryParam();
        queryParam1.setParamName("id");
        queryParam1.setAliasName(queryData1.getTableName() + UuidUtil.generateUuid());
        List<String> dataList1 = new ArrayList<>();
        dataList1.add("9");
        dataList1.add("10");
        queryParam1.setDataList(dataList1);
        queryParams1.add(queryParam1);
        queryData1.setQueryParams(queryParams1);
        queryDataList.add(queryData1);

        SqlSchemeTwoUtil sqlSchemeTwoUtil = new SqlSchemeTwoUtil(queryDataList);
        SqlReturn sqlReturn = sqlSchemeTwoUtil.updateSql(sql);
        log.info("结果 ===> {}", sqlReturn.getSql());
    }

    /**
     * 修改sql 语句
     *
     * @param sql 语句
     * @return 结果
     */
    public SqlReturn updateSql(String sql) {
        SqlReturn sqlReturn = new SqlReturn();
        SqlParser sqlParser = SqlParser.create(sql, config);
        try {
            SqlNode sqlNode = sqlParser.parseQuery();
            log.info("更改之前的sqlNode ===> {}", sqlNode.toString());
            Map<String, List<QueryParam>> stringListMap = handlerSQL(sqlNode);
            String s = sqlNode.toString();
            if (StrUtil.isNotBlank(s)) {
                s = s.replace("`", "");
            }
            log.info("更改之后的sqlNode ===> {}", s);
            log.info("增加的查询条件 ===> {}", JSON.toJSONString(stringListMap));
            sqlReturn.setSql(s);
            List<String> parmaList = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(stringListMap)) {
                for (Map.Entry<String, List<QueryParam>> stringListEntry : stringListMap.entrySet()) {
                    List<QueryParam> value = stringListEntry.getValue();
                    if (CollectionUtil.isNotEmpty(value)) {
                        for (QueryParam queryParam : value) {
                            parmaList.add(queryParam.getParamName());
                        }
                    }
                }
            }
            sqlReturn.setParamList(parmaList);
            return sqlReturn;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }



    // TODO 解析sql
    private Map<String, List<QueryParam>> handlerSQL(SqlNode sqlNode) {
        SqlKind kind = sqlNode.getKind();
        Map<String, List<QueryParam>> queryParams = new ConcurrentHashMap<>();
        switch (kind) {
            case IDENTIFIER:
                //最终的表名
                SqlIdentifier sqlIdentifier = (SqlIdentifier) sqlNode;
                String tableName = sqlIdentifier.toString();
                log.info("==from table name===>{}", tableName);
                break;
            case AS:
                // 带别名的表名
                // 也可能是子查询
                SqlBasicCall sqlBasicCall = (SqlBasicCall) sqlNode;
                SqlNode selectNode = sqlBasicCall.getOperandList().get(0);
                // 获取别名
                String alias = sqlBasicCall.getOperandList().get(1).toString();
                Map<String, List<QueryParam>> stringListMap = handlerSQL(selectNode);
                if (CollectionUtil.isEmpty(stringListMap)) {
                    break;
                }
                List<QueryParam> queryParamList = new ArrayList<>();
                for (Map.Entry<String, List<QueryParam>> stringListEntry : stringListMap.entrySet()) {
                    List<QueryParam> value = stringListEntry.getValue();
                    queryParamList.addAll(value);
                }
                queryParams.put(alias, queryParamList);
                break;
            case SELECT:
                queryParams = handlerSelect(sqlNode);
                break;
            case UNION:
                for (SqlNode node : ((SqlBasicCall) sqlNode).getOperandList()) {
                    handlerSQL(node);
                }
                break;
            case ORDER_BY:
                handlerOrderBy(sqlNode);
                break;

        }
        return queryParams;
    }

    // TODO 解析排序
    private void handlerOrderBy(SqlNode node) {
        SqlOrderBy sqlOrderBy = (SqlOrderBy) node;
        SqlNode query = sqlOrderBy.query;
        handlerSQL(query);
        SqlNodeList orderList = sqlOrderBy.orderList;
    }

    // TODO 解析查询
    private Map<String, List<QueryParam>> handlerSelect(SqlNode select) {
        SqlSelect sqlSelect = (SqlSelect) select;
        if (sqlSelect == null) {
            return null;
        }
        Map<String, List<QueryParam>> queryParamMapReturn = new ConcurrentHashMap<>();
        // select 后面要有from
        SqlNode from = sqlSelect.getFrom();
        // 需要添加的字段
        Map<String, List<QueryParam>> queryParams = handlerFrom(from);
        if (CollectionUtil.isEmpty(queryParams)) {
            log.info("无变更");
            return null;
        }
        // 已有的查询条件
        SqlNodeList selectList = sqlSelect.getSelectList();
        List<SqlNode> list = selectList.getList();
        List<QueryParam> queryParamListReturn = new ArrayList<>();
        for (Map.Entry<String, List<QueryParam>> stringListEntry : queryParams.entrySet()) {
            String tableName = stringListEntry.getKey();
            List<QueryParam> queryParamList = stringListEntry.getValue();
            if (CollectionUtil.isNotEmpty(queryParamList)) {
                for (QueryParam queryParam : queryParamList) {
                    String paramName = queryParam.getParamName();
                    String aliasName = queryParam.getAliasName();
                    List<String> dataList = queryParam.getDataList();
                    // 构建表名和字段名
                    List<String> paramNameList = new ArrayList<>();
                    paramNameList.add(tableName);
                    paramNameList.add(paramName);
                    SqlIdentifier sqlIdentifierLeft = new SqlIdentifier(paramNameList, ZERO);
                    // 构建别名
                    SqlIdentifier sqlIdentifierRight = new SqlIdentifier(aliasName, ZERO);
                    List<SqlNode> sqlNodeList = new ArrayList<>();
                    sqlNodeList.add(sqlIdentifierLeft);
                    sqlNodeList.add(sqlIdentifierRight);
                    SqlBasicCall sqlBasicCall = new SqlBasicCall(sqlAsOperator, sqlNodeList, ZERO);
                    list.add(sqlBasicCall);

                    // 新的返回的数据
                    QueryParam queryParamReturn = new QueryParam();
                    queryParamReturn.setParamName(aliasName);
                    queryParamReturn.setAliasName(tableName + UuidUtil.generateUuid());
                    queryParamReturn.setDataList(dataList);
                    queryParamListReturn.add(queryParamReturn);
                }
            }
        }
        queryParamMapReturn.put(UuidUtil.generateUuid(), queryParamListReturn);
        if (sqlSelect.hasWhere()) {
            if (CollectionUtil.isNotEmpty(queryParams)) {
                SqlBasicCall sqlBasicCallNew = getQueryParams(queryParams);
                // 获取已有的查询条件
                SqlNode where = sqlSelect.getWhere();
                SqlBasicCall sqlBasicCallOld = (SqlBasicCall) where;
                List<SqlBasicCall> finallyList = new ArrayList<>();
                finallyList.add(sqlBasicCallOld);
                finallyList.add(sqlBasicCallNew);
                SqlBasicCall sqlBasicCall3 = new SqlBasicCall(sqlAndOperator, finallyList, ZERO);
                sqlSelect.setWhere(sqlBasicCall3);
            }
        } else {
            SqlBasicCall sqlBasicCall = getQueryParams(queryParams);
            sqlSelect.setWhere(sqlBasicCall);
        }
        log.info("更改之后的select sql ===> {}", sqlSelect);
        log.info("返回的select查询字段为 ===> {}", JSON.toJSONString(queryParamMapReturn));
        return queryParamMapReturn;
    }

    /**
     * TODO 解析from语句
     * 从from中获取需要加字段的表名
     * 找到之后传回给select中，并在参数中加入字段，
     * 因为存在连接所以有多个字段，因为可能存在别名，
     * 所以加字段需要带别名
     *
     * @param from from语句
     */
    private Map<String, List<QueryParam>> handlerFrom(SqlNode from) {
        if (from == null) {
            return null;
        }
        SqlKind kind = from.getKind();
        Map<String, List<QueryParam>> queryParamList = new ConcurrentHashMap<>();
        switch (kind) {
            case IDENTIFIER:
                // 直接是表名
                queryParamList = handlerIdentifier(null, from);
                break;
            case AS:
                // 带别名的表名
                // 也可能是子查询
                queryParamList = handlerAs(from);
                break;
            case JOIN:
                queryParamList = handlerSqlJoin((SqlJoin) from);
                break;
            case SELECT:
                handlerSQL(from);
                break;
        }
        return queryParamList;
    }

    private Map<String, List<QueryParam>> handlerIdentifier(String tableNameOrAlias, SqlNode sqlNode) {
        Map<String, List<QueryParam>> queryParamList = new ConcurrentHashMap<>();
        SqlIdentifier sqlIdentifier = (SqlIdentifier) sqlNode;
        String tableName = sqlIdentifier.toString();
        log.info("==from table name===>{}", tableName);
        List<QueryParam> queryParamByTableName = getQueryParamByTableName(tableName);
        if (tableNameOrAlias != null) {
            tableName = tableNameOrAlias;
        }
        queryParamList.put(tableName, queryParamByTableName);
        return queryParamList;
    }

    private Map<String, List<QueryParam>> handlerAs(SqlNode sqlNode) {
        Map<String, List<QueryParam>> queryParamMapList = new ConcurrentHashMap<>();
        SqlBasicCall sqlBasicCall = (SqlBasicCall) sqlNode;
        SqlNode selectNode = sqlBasicCall.getOperandList().get(0);
        // 获取别名
        String alias = sqlBasicCall.getOperandList().get(1).toString();
        // 如果直接是表名则，返回别名和查询参数
        if (selectNode.getKind().equals(IDENTIFIER)) {
            queryParamMapList = handlerIdentifier(alias, selectNode);
        } else {
            Map<String, List<QueryParam>> stringListMap = handlerSQL(selectNode);
            if (CollectionUtil.isEmpty(stringListMap)) {
                return queryParamMapList;
            }
            List<QueryParam> queryParamList = new ArrayList<>();
            for (Map.Entry<String, List<QueryParam>> stringListEntry : stringListMap.entrySet()) {
                List<QueryParam> value = stringListEntry.getValue();
                queryParamList.addAll(value);
            }
            queryParamMapList.put(alias, queryParamList);
        }
        return queryParamMapList;
    }

    // 处理join语句
    private Map<String, List<QueryParam>> handlerSqlJoin(SqlJoin sqlJoin) {
        Map<String, List<QueryParam>> queryParamList = new ConcurrentHashMap<>();
        SqlNode left = sqlJoin.getLeft();
        SqlKind leftKind = left.getKind();
        switch (leftKind) {
            case IDENTIFIER:
                queryParamList.putAll(handlerIdentifier(null, left));
                break;
            case JOIN:
                queryParamList.putAll(handlerSqlJoin((SqlJoin) left));
                break;
            case AS:
                queryParamList.putAll(handlerAs(left));
                break;
        }
        SqlNode right = sqlJoin.getRight();
        SqlKind rightKind = right.getKind();
        switch (rightKind) {
            case IDENTIFIER:
                queryParamList.putAll(handlerIdentifier(null, right));
                break;
            case AS:
                queryParamList.putAll(handlerAs(right));
                break;
        }
        return queryParamList;
    }

    private SqlBasicCall getQueryParams(Map<String, List<QueryParam>> queryParams) {
        if (CollectionUtil.isEmpty(queryParams)) {
            return null;
        }
        List<SqlBasicCall> sqlBasicCallList = new ArrayList<>();
        for (Map.Entry<String, List<QueryParam>> stringListEntry : queryParams.entrySet()) {
            String tableName = stringListEntry.getKey();
            List<QueryParam> value = stringListEntry.getValue();

            for (QueryParam queryParam : value) {
                String paramName = queryParam.getParamName();
                String aliasName = queryParam.getAliasName();
                List<String> dataList = queryParam.getDataList();

                // 构建查询条件的等式左边
                List<SqlNode> list = new ArrayList<>();
                List<String> paramNameList = new ArrayList<>();
                paramNameList.add(tableName);
                paramNameList.add(paramName);
                SqlIdentifier sqlIdentifierLeft = new SqlIdentifier(paramNameList, ZERO);
                list.add(sqlIdentifierLeft);

                // 构建查询条件的等式右边
                List<SqlNode> valueList = new ArrayList<>();
                for (String s : dataList) {
                    NlsString nlsString = new NlsString(s, null, null);
                    SqlCharStringLiteral2 sqlCharStringLiteral2 = new SqlCharStringLiteral2(nlsString, ZERO);
                    valueList.add(sqlCharStringLiteral2);
                }

                SqlNodeList sqlNodeList = new SqlNodeList(valueList, ZERO);
                list.add(sqlNodeList);
                SqlBasicCall sqlBasicCall = new SqlBasicCall(sqlInOperator, list, ZERO);
                sqlBasicCallList.add(sqlBasicCall);
            }
        }
        return getSqlBasicCall(sqlBasicCallList);
    }

    private SqlBasicCall getSqlBasicCall(List<SqlBasicCall> sqlBasicCallList) {
        if (CollectionUtil.isEmpty(sqlBasicCallList)) {
            return null;
        }
        if (sqlBasicCallList.size() == 1) {
            return sqlBasicCallList.get(0);
        }
        if (sqlBasicCallList.size() == 2) {
            return new SqlBasicCall(sqlAndOperator, sqlBasicCallList, ZERO);
        }
        if (sqlBasicCallList.size() > 2) {
            List<SqlBasicCall> newList = sqlBasicCallList.subList(0, 2);
            SqlBasicCall sqlBasicCall = new SqlBasicCall(sqlAndOperator, newList, ZERO);
            for (int i = 2; i < sqlBasicCallList.size(); i++) {
                newList = new ArrayList<>();
                newList.add(sqlBasicCall);
                newList.add(sqlBasicCallList.get(i));
                sqlBasicCall = new SqlBasicCall(sqlAndOperator, newList, ZERO);
            }
            return sqlBasicCall;
        }
        return null;
    }
}
