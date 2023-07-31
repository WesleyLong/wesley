package com.example.demo;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang yaowen
 * @version 1.0
 * @date 2020/5/31 下午12:34
 */

@Component
public class Test9 {
    public static void main(String[] args) throws Exception {
        String token = JSON.parseObject(getToken()).get("access_token").toString();
//        String token = "64_QZxGRIAkBOULkUS3LcsUJj-2yUYIrXZHKMVOOL1qcwykigQPheo8Hj2OLZ85qtbSpeyrOIwcjXgX11zpoSBI0DndGxLi6k6T4u_C6H7bDoafEAAXlKTegePmJGMTXTfAHAZDZ";
//        System.out.println(getContentList(token));
        System.out.println(getDraftList(token));
//        System.out.println(getDraftCount(token));
//        System.out.println(getArticle(token,"wxNK0TGlXr1Y3v_LjEhyWEsRYl3p0lppgvXoST6B9_GkTwIioYbsXtHtpRGSyH4i"));
    }

    private static String getToken() throws Exception {
        // access_token接口https请求方式: GET https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
        String path = " https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
//        String appid = "wx3e5deee603b414c1";//公司的
//        String secret = "0960deaa682ea60158738fbf09d3b23f";//公司的
        String appid = "wxacfc47cd17160703";//我的
        String secret = "89e1093088b78a9e60da266aeb414e63";//我的
        URL url = new URL(path + "&appid=" + appid + "&secret=" + secret);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        InputStream in = connection.getInputStream();
        byte[] b = new byte[100];
        int len;
        StringBuffer sb = new StringBuffer();
        while ((len = in.read(b)) != -1) {
            sb.append(new String(b, 0, len));
        }

        System.out.println(sb);
        in.close();
        return sb.toString();
    }

    private static String getContentList(String token) throws IOException {
        String path = "https://api.weixin.qq.com/cgi-bin/freepublish/batchget?access_token=" + token;
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("content-type", "application/json;charset=utf-8");
        connection.connect();
        // post发送的参数
        Map<String, Object> map = new HashMap<>();
        map.put("offset", 0);
        map.put("count", 20);
        map.put("no_content", 1);
        // 将map转换成json字符串
        String paramBody = JSON.toJSONString(map); // 这里用了Alibaba的fastjson

        OutputStream out = connection.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        bw.write(paramBody); // 向流中写入参数字符串
        bw.flush();

        InputStream in = connection.getInputStream();
        byte[] b = new byte[100];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = in.read(b)) != -1) {
            sb.append(new String(b, 0, len));
        }

        in.close();
        return sb.toString();
    }

    private static String getDraftList(String token) throws IOException {
        String path = "https://api.weixin.qq.com/cgi-bin/draft/batchget?access_token=" + token;
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("content-type", "application/json;charset=utf-8");
        connection.connect();
        // post发送的参数
        Map<String, Object> map = new HashMap<>();
        map.put("offset", 0);
        map.put("count", 10);
        map.put("no_content", 1);
        // 将map转换成json字符串
        String paramBody = JSON.toJSONString(map); // 这里用了Alibaba的fastjson

        OutputStream out = connection.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        bw.write(paramBody); // 向流中写入参数字符串
        bw.flush();

        InputStream in = connection.getInputStream();
        byte[] b = new byte[10000];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = in.read(b)) != -1) {
            sb.append(new String(b, 0, len));
        }

        in.close();
        return sb.toString();
    }

    private static String getDraftCount(String token) throws IOException {
        String path = "https://api.weixin.qq.com/cgi-bin/draft/count?access_token=" + token;
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("content-type", "application/json;charset=utf-8");
        connection.connect();

        InputStream in = connection.getInputStream();
        byte[] b = new byte[10000];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = in.read(b)) != -1) {
            sb.append(new String(b, 0, len));
        }

        in.close();
        return sb.toString();
    }

    private static String getArticle(String token,String articleId) throws IOException {
        String path = "https://api.weixin.qq.com/cgi-bin/freepublish/getarticle?access_token=" + token;
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("content-type", "application/json;charset=utf-8");
        connection.connect();
        // post发送的参数
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("article_id", articleId);
        // 将map转换成json字符串
        String paramBody = JSON.toJSONString(map); // 这里用了Alibaba的fastjson

        OutputStream out = connection.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        bw.write(paramBody); // 向流中写入参数字符串
        bw.flush();

        InputStream in = connection.getInputStream();
        byte[] b = new byte[100];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = in.read(b)) != -1) {
            sb.append(new String(b, 0, len));
        }

        in.close();
        return sb.toString();
    }
}

