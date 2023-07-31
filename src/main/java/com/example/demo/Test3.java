package com.example.demo;

import com.example.demo.taosdb.GetConnection;
import com.example.demo.util.ResultSetUtil;

import java.sql.ResultSet;
import java.sql.Statement;

public class Test3 {
    public static void main(String[] args) throws Exception {
        Statement stmt = GetConnection.getCloudConnectionAL();
        ResultSet resultSet = stmt.executeQuery("SELECT SERVER_STATUS()");
        ResultSetUtil.print(resultSet);
    }
}
