package com.example.demo.taosdb;

public class Constant {
    public static final String cloudJdbcUrl = "jdbc:TAOS-RS://gw.us-east-1.aws.cloud.tdengine.com:80?usessl=false&token=e3e6f0e93360a285da3b19c66e81f0fea4f7a089";

    public static final String cloudJdbcUrlAL = "jdbc:TAOS-RS://gw.cloud.taosdata.com:443?useSSL=true&token=169800c8e6581549940e0331c04bc017f4ca850f";

    public static final String localJdbcUrl_3_0_rs = "jdbc:TAOS-RS://192.168.229.128:6041/information_schema?user=root&password=taosdata";

    public static final String localJdbcUrl_2_6_rs = "jdbc:TAOS-RS://192.168.229.128:6042/log?user=root&password=taosdata";

    public static final String localJdbcUrl_3_0 = "jdbc:TAOS://192.168.229.128:6030?user=root&password=taosdata";

    public static final String localJdbcUrl_2_6 = "jdbc:TAOS://192.168.229.128:6031?user=root&password=taosdata";
}
