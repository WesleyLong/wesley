package com.example.demo.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetUtil {
    public static void print(ResultSet resultSet) throws SQLException {
        //下标移动到最后一行取行数，得到查询记录条数
        resultSet.last();
        int rowSize = resultSet.getRow();
        System.out.printf("一共有%s条记录%n", rowSize);
        //下标移到最开始，否则之后无法遍历数据
        resultSet.beforeFirst();

        //字段数（列数）
        int columnCount = resultSet.getMetaData().getColumnCount();
        System.out.printf("每条记录有%s个字段%n", columnCount);

        //字段名称
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-30s\t", resultSet.getMetaData().getColumnName(i));
        }
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String className = resultSet.getObject(i).getClass().getName();
                if (className.equalsIgnoreCase("[B")) {
                    System.out.printf("%-30s\t", resultSet.getString(i));
                } else {
                    System.out.printf("%-30s\t", resultSet.getObject(i));
                }
            }
            System.out.println();
        }
    }
}
