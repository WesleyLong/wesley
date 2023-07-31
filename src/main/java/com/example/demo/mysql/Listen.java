package com.example.demo.mysql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过监听mysql的bin-log实现对数据库变动的实时监听
 * 监听到的事件分为很多种，主要关心的是增删改
 * 但因为这三种事件没有库名和表名，所以要先拿到TableMapEventData获取库名和表名缓存起来之后做筛选
 * 也可以缓存tableId，但tableId并不一定和表是一一对应的关系，一定条件下会发生改变
 */
public class Listen {
    /**
     * 连接mysqlBinLog
     */
    public static void init() {
        BinaryLogClient client = new BinaryLogClient("10.40.92.85", 3306, "root", "zerocarbon");
        client.setServerId(2);
        HashMap<String, String> map = new HashMap<>();
        client.registerEventListener(event -> {
            EventData data = event.getData();
            TableMapEventData tableMapEventData;
            if (data instanceof TableMapEventData) {
                tableMapEventData = (TableMapEventData) data;
                String database = tableMapEventData.getDatabase();
                String table = tableMapEventData.getTable();
                if (table.equals("student_1")) {
                    System.out.println(database + ":" + table);
                    map.put("database", database);
                    map.put("table", table);
                    System.out.println(tableMapEventData.getEventMetadata());
                } else {
                    return;
                }
            }
            if (map.getOrDefault("table", "").equals("student_1") && data instanceof UpdateRowsEventData) {
                System.out.println("Update:");
                System.out.println(data);
                UpdateRowsEventData updateRowsEventData = (UpdateRowsEventData) data;
                for (Map.Entry<Serializable[], Serializable[]> row : updateRowsEventData.getRows()) {
                    List<Serializable> entries = Arrays.asList(row.getValue());
                    System.out.println(entries);
                    JSONObject dataObject = getDataObject(entries);
                    System.out.println(dataObject);
                }
                map.remove("table");
            } else if (map.getOrDefault("table", "").equals("student_1") && data instanceof WriteRowsEventData) {
                System.out.println("Insert:");
                System.out.println(data);
                map.remove("table");
            } else if (map.getOrDefault("table", "").equals("student_1") && data instanceof DeleteRowsEventData) {
                System.out.println("Delete:");
                System.out.println(data);
                map.remove("table");
            }
        });
        try {
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getDataObject(List<Serializable> message) {
        JSONObject resultObject = new JSONObject();
        String format = "{\"id\":\"0\",\"name\":\"1\",\"score\":\"2\"}";
        JSONObject json = JSON.parseObject(format);
        for (String key : json.keySet()) {
            resultObject.put(key, message.get(json.getInteger(key)));
        }
        return resultObject;
    }

    public static void main(String[] args) {
        init();
    }
}
