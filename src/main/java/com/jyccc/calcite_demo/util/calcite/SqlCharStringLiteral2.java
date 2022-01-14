package com.jyccc.calcite_demo.util.calcite;

import org.apache.calcite.sql.SqlCharStringLiteral;
import org.apache.calcite.sql.parser.SqlParserPos;
import org.apache.calcite.util.NlsString;

public class SqlCharStringLiteral2 extends SqlCharStringLiteral {

    public SqlCharStringLiteral2(NlsString val, SqlParserPos pos) {
        super(val, pos);
    }
}
