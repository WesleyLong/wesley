package com.example.demo.taosdb.fastwrite.newenergy;

import cn.hutool.core.date.DateUtil;
import com.example.demo.taosdb.Constant;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class RestInsertExample {
    private static final String dbName = "newenergy";
    private static final String stbName = "predict";
    private static final Long fieldId = 1575061352063970000L;

    private static Connection getConnection() throws SQLException {
        String jdbcUrl = Constant.localJdbcUrl_2_6_rs;
        return DriverManager.getConnection(jdbcUrl);
    }

    private static List<PredictData> getRawData() {
        List<PredictData> list = new ArrayList<>();
        Date date = new Date();
        for (int i = 1; i <= 16; i++) {
            PredictData data = new PredictData();
            data.setTs(DateUtil.offsetMinute(date, 15 * i));
            data.setAreaId(1L);
            data.setGroupId(2L);
            data.setBatch(date.getTime());
            data.setFieldId(fieldId);
            data.setPreFix(i);
            data.setSpeed(BigDecimal.valueOf(Math.random()).setScale(4, RoundingMode.HALF_UP).doubleValue());
            data.setPower(BigDecimal.valueOf(Math.random()).setScale(4, RoundingMode.HALF_UP).doubleValue());
            data.setEnergy(BigDecimal.valueOf(Math.random()).setScale(4, RoundingMode.HALF_UP).doubleValue());
            list.add(data);
        }
        return list;
    }

    @Data
    static class PredictData {
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date ts;
        private int preFix;
        private Double speed;
        private Double power;
        private Double energy;
        private Long fieldId;
        private Long groupId;
        private Long areaId;
        private Long batch;
    }

    private static String getSQL() {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        long dateTime = System.currentTimeMillis() / 1000 * 1000;
        for (PredictData item : getRawData()) {
            String tbName = dbName + ".t" + fieldId + "_" + item.getPreFix();
            sb.append(tbName) //tbname
                    .append(" USING newenergy.predict TAGS(")
                    .append(fieldId).append(", ") // tag: fieldId-风场id
                    .append(1).append(", ") // tag: groupId-分公司id
                    .append(2) // tag: areaId-区域id
                    .append(") VALUES(")
                    .append('\'').append(item.getTs()).append('\'').append(",") // ts
                    .append(item.getSpeed()).append(",") // speed
                    .append(item.getPower()).append(",") // power
                    .append(item.getEnergy()).append(",")
                    .append(dateTime)
                    .append(") "); // energy
        }
        return sb.toString();
    }

//    @Scheduled(cron = "0 */15 * * * ?")
    public static void insertUshortData() throws SQLException {
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                String sql = getSQL();
                System.out.println(sql);
                int rowCount = stmt.executeUpdate(sql);
                System.out.println("rowCount=" + rowCount); // rowCount=8
            }
        }
    }

//    @Scheduled(cron = "0 0 6 * * ?")
    public static void insertShortData() throws SQLException {
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                String sql = getSQL();
                System.out.println(sql);
                int rowCount = stmt.executeUpdate(sql);
                System.out.println("rowCount=" + rowCount); // rowCount=8
            }
        }
    }

//    @Scheduled(cron = "0 0 6 1/15 * ?")
    public static void insertMLData() throws SQLException {
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                String sql = getSQL();
                System.out.println(sql);
                int rowCount = stmt.executeUpdate(sql);
                System.out.println("rowCount=" + rowCount); // rowCount=8
            }
        }
    }

    //    @Scheduled(cron = "*/1 * * * * ?")
    public static void a() throws SQLException {
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                String sql = getSQL();
                System.out.println(sql);
                int rowCount = stmt.executeUpdate(sql);
                System.out.println("rowCount=" + rowCount); // rowCount=8
            }
        }
    }

    public static void initDB() throws SQLException {
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP DATABASE IF EXISTS " + dbName);
                stmt.execute("CREATE DATABASE " + dbName + " UPDATE 2");//UPDATE为2 数据可更新
                stmt.execute("CREATE STABLE newenergy.predict (ts TIMESTAMP, speed FLOAT, power FLOAT, energy FLOAT, batch BIGINT) " +
                        "TAGS (fieldId BIGINT, groupId BIGINT, areaId BIGINT)");
            }
        }
    }

    static void initShortDB() throws SQLException {
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE STABLE newenergy.short_predict (ts TIMESTAMP, speed FLOAT, power FLOAT, energy FLOAT, batch BIGINT) " +
                        "TAGS (fieldId BIGINT, groupId BIGINT, areaId BIGINT)");
            }
        }
    }

    static void initMLDB() throws SQLException {
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE STABLE newenergy.ml_predict (ts TIMESTAMP, speed FLOAT, power FLOAT, energy FLOAT, batch BIGINT) " +
                        "TAGS (fieldId BIGINT, groupId BIGINT, areaId BIGINT)");
            }
        }
    }


    public static void main(String[] args) throws SQLException {
        initDB();
//        insertData();
    }
}
