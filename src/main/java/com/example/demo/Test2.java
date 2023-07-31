package com.example.demo;

import com.example.demo.taosdb.GetConnection;

import java.sql.ResultSet;
import java.sql.Statement;

public class Test2 {
    public static void main(String[] args) throws Exception {
//        Statement stmt = GetConnection.getLocalConnection_3_0();
        Statement stmt = GetConnection.getLocalConnection_2_6_rs();
        stmt.execute("use test");
//        ResultSet resultSet = stmt.executeQuery("show stables;");
        ResultSet resultSet = stmt.executeQuery("select count(*) from test.meters;");
//        ResultSetUtil.print(resultSet);
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1));
        }
    }
}
