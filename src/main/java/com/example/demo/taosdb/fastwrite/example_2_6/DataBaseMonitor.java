package com.example.demo.taosdb.fastwrite.example_2_6;

import com.example.demo.taosdb.Constant;

import java.sql.*;

/**
 * Prepare target database.
 * Count total records in database periodically so that we can estimate the writing speed.
 */
public class DataBaseMonitor {
    private Connection conn;
    private Statement stmt;

    public DataBaseMonitor init() throws SQLException {
        if (conn == null) {
//            String jdbcURL = System.getenv("TDENGINE_JDBC_URL");
            String jdbcURL = Constant.localJdbcUrl_2_6_rs;
            conn = DriverManager.getConnection(jdbcURL);
            stmt = conn.createStatement();
        }
        return this;
    }

    public void close() {
        try {
            stmt.close();
        } catch (SQLException e) {
        }
        try {
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void prepareDatabase() throws SQLException {
        stmt.execute("DROP DATABASE IF EXISTS test");
        stmt.execute("CREATE DATABASE test");
        stmt.execute("CREATE STABLE test.meters (ts TIMESTAMP, current FLOAT, voltage INT, phase FLOAT) TAGS (location BINARY(64), groupId INT)");
    }

    public Long count() throws SQLException {
        if (!stmt.isClosed()) {
            ResultSet result = stmt.executeQuery("SELECT count(*) from test.meters");
            result.next();
            return result.getLong(1);
        }
        return null;
    }

    /**
     * show test.stables;
     * <p>
     * name              |      created_time       | columns |  tags  |   tables    |
     * ============================================================================================
     * meters           | 2022-07-20 08:39:30.902 |       4 |      2 |      620000 |
     *
     *
     * 2.6版本下 show test.meters 此命令在CLI界面有上面的结果，但是查询返回的ResultSet却只有表头和空数据，不知为何
     * 改为下面的sql语句来查询子表的数量
     * 3.0以后的版本改为从information_schema中相关的表查询
     */
    public Long getTableCount() throws SQLException {
        if (!stmt.isClosed()) {
            ResultSet result = stmt.executeQuery("SELECT COUNT(*) FROM (SELECT DISTINCT TBNAME FROM test.meters);");
            result.next();
            return result.getLong(1);
        }
        return null;
    }
}
