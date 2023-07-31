package com.example.demo.orm;

import io.swagger.annotations.ApiModelProperty;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateSqlFromEntity {

    public static void main(String[] a) throws IOException {
        Class<CaKpiParkTargetData> klass = CaKpiParkTargetData.class;
        // 生成的sql语句的位置
        String outputPath = "D:/sql/ca_kpi_park_target_data.txt";

        generateTableSql(klass, outputPath, "ca_kpi_park_target_data");
        System.out.println("生成结束");
    }

    public static void writeFile(String content, String outputPath) throws IOException {
        File file = new File(outputPath);
        System.out.println("文件路径: " + file.getAbsolutePath());
        // 输出文件的路径
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                System.out.println("路径不存在，创建路径失败");
            }
        }
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter out = null;

        try {
            // 如果文件存在，就删除
            if (file.exists()) {
                if (!file.delete()) {
                    System.out.println("删除文件失败，请确认文件是否存在或被占用");
                }
            }
            if (!file.createNewFile()) {
                System.out.println("创建文件失败");
                return;
            }
            fos = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fos);
            out = new BufferedWriter(osw);
            out.write(content);
            // 清空缓冲流，把缓冲流里的文本数据写入到目标文件里
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(fos).close();
            Objects.requireNonNull(osw).close();
            Objects.requireNonNull(out).close();
        }
    }

    public static void generateTableSql(Class obj, String outputPath, String tableName) throws IOException {
        // tableName 如果是 null，就用类名做表名
        if (tableName == null || tableName.equals("")) {
            tableName = obj.getName();
            tableName = tableName.substring(tableName.lastIndexOf(".") + 1);
        }

//        tableName = tableName.toUpperCase();// 表名用大写字母
        System.out.println(tableName);

        Field[] fields = obj.getDeclaredFields();
        Object param;
        String column;

        StringBuilder sb = new StringBuilder();

        sb.append("drop table if exists ").append(tableName).append(";\r\n");

        sb.append("\r\n");

        sb.append("create table ").append(tableName).append("(\r\n");

        boolean firstId = true;

        for (Field f : fields) {
            column = xX2x_x(f.getName());
            param = f.getType();
            sb.append("`").append(column).append("`"); // 一般第一个是主键
            if (param.toString().equalsIgnoreCase(Integer.class.toString())) {
                sb.append(" bigint ");
            } else if (param.toString().equalsIgnoreCase(Double.class.toString())) {
                sb.append(" double ");
            } else if (param.toString().equalsIgnoreCase(String.class.toString())) {
                // 注意：根据需要，自行修改 varchar 的长度。这里设定为长度等于 50
                int length = 500;
                sb.append(" varchar(").append(length).append(")");
            }

            if (firstId) {
                sb.append(" PRIMARY KEY AUTO_INCREMENT ");
                firstId = false;
            }

            // 获取属性的所有注释
            Annotation[] allAnnotations = f.getAnnotations();

            for (Annotation an : allAnnotations) {
                sb.append(" COMMENT '");
                param = ((ApiModelProperty) an).value();
                sb.append(param).append("',");
            }
        }
        sb.append("`delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识（0.未删除、1.已删除）',\n" +
                "  `create_user` bigint DEFAULT NULL COMMENT '创建人id',\n" +
                "  `update_user` bigint DEFAULT NULL COMMENT '修改人id',\n" +
                "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `update_time` datetime DEFAULT NULL COMMENT '修改时间'\n" +
                "  )ENGINE = INNODB DEFAULT CHARSET = utf8;\n");

        writeFile(sb.toString(), outputPath);
    }

    /**
     * @return java.lang.String
     * @Description 将驼峰转为下划线
     * @Date 2022/4/22 13:11
     * @since 1.0.0
     */
    public static String xX2x_x(String str) {
        Pattern compile = Pattern.compile("[A-Z]");
        Matcher matcher = compile.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * @return java.lang.String
     * @Description 将下划线转为驼峰
     * @Date 2022/4/22 13:12
     * @since 1.0.0
     */
    public static String x_x2xX(String str) {
        str = str.toLowerCase();
        Pattern compile = Pattern.compile("_[a-z]");
        Matcher matcher = compile.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(0).toUpperCase().replace("_", ""));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


}

