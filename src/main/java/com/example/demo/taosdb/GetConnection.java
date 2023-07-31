package com.example.demo.taosdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GetConnection {
    private static Connection conn;
    private static Statement stmt;

    public static Statement getCloudConnection() throws SQLException {
        return getStmt(Constant.cloudJdbcUrl);
    }

    public static Statement getCloudConnectionAL() throws SQLException {
        return getStmt(Constant.cloudJdbcUrlAL);
    }

    public static Statement getLocalConnection_2_6_rs() throws Exception {
        Class.forName("com.taosdata.jdbc.rs.RestfulDriver");
        return getStmt(Constant.localJdbcUrl_2_6_rs);
    }

    public static Statement getLocalConnection_3_0_rs() throws Exception {
        Class.forName("com.taosdata.jdbc.rs.RestfulDriver");
        return getStmt(Constant.localJdbcUrl_3_0_rs);
    }

    public static Statement getLocalConnection_2_6() throws Exception {
        Class.forName("com.taosdata.jdbc.TSDBDriver");
        return getStmt(Constant.localJdbcUrl_2_6);
    }

    public static Statement getLocalConnection_3_0() throws Exception {
        Class.forName("com.taosdata.jdbc.TSDBDriver");
        return getStmt(Constant.localJdbcUrl_3_0);
    }

    public static Statement getStmt(String jdbcURL) throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(jdbcURL);
            stmt = conn.createStatement();
        }
        return stmt;
    }
}
