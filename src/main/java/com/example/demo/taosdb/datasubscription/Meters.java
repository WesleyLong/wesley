package com.example.demo.taosdb.datasubscription;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Meters {
    private Timestamp ts;
    private float current;
    private int voltage;
    private int groupId;
    private String location;

    @Override
    public String toString() {
        return "Meters{" +
                "ts=" + ts +
                ", current=" + current +
                ", voltage=" + voltage +
                ", groupId=" + groupId +
                ", location='" + location + '\'' +
                '}';
    }
}
