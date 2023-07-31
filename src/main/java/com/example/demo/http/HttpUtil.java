package com.example.demo.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    /**
     * get请求
     *
     * @return String
     */
    public static String doGet(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //读取返回数据
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    /**
     * post请求
     *
     * @param params key-value格式
     * @return String
     */
    public static String doPost(String url, Map params) {
        BufferedReader in;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));
            //设置参数
            List<NameValuePair> nvps = new ArrayList<>();
            for (Object o : params.keySet()) {
                String name = (String) o;
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {   //请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(), StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder("");
                String line;
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line).append(NL);
                }
                in.close();
                return sb.toString();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * post请求（用于请求json格式的参数）
     *
     * @param params String
     * @return String
     */
    public static String doPost(String url, String params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(2000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        StringEntity entity = new StringEntity(params, StandardCharsets.UTF_8);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return new String(jsonString.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 发送https请求，无参数携带,但携带头部信息
     *
     * @param reqUrl        String
     * @param param         参数信息
     * @param requestMethod get，post 默认是get
     * @param header        请求头信息
     * @param connTimeout   请求超时时间 默认10秒
     * @param readTimeout   读取超时时间 默认10秒
     * @return String
     */
    public static String doHttps(String reqUrl, String param, String requestMethod, List<Map<String, String>> header,
                                 String contentType, Integer connTimeout, Integer readTimeout) {
        URL url;
        InputStream inputStream = null;
        StringBuilder result = new StringBuilder();
        OutputStream outputStream = null;
        try {
            url = new URL(reqUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = {xtm};
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);
            con.setSSLSocketFactory(ctx.getSocketFactory());
            if (header != null && header.size() != 0) {
                for (Map<String, String> map : header) {
                    String key = map.keySet().iterator().next();
                    con.setRequestProperty(map.keySet().iterator().next(), map.get(key));
                }
            }
            con.setHostnameVerifier((arg0, arg1) -> true);
            // 发送POST请求必须设置为true
            con.setDoInput(true); //允许输入流，即允许下载
            con.setDoOutput(false);//默认为false.允许输出流 GET下需设置为false
            con.setRequestMethod("GET"); //使用get请求
            con.setUseCaches(false); //不使用缓冲
            // 设置请求超时时间和读取超时时间
            con.setConnectTimeout(connTimeout == null ? 10000 : connTimeout);
            con.setReadTimeout(readTimeout == null ? 10000 : readTimeout);
            // 设置通用的请求属性
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            if (contentType != null) {
                con.setRequestProperty("Content-type", contentType);
            }
            if ("POST".equalsIgnoreCase(requestMethod)) {
                con.setDoOutput(true); //允许输出流，即允许上传
                con.setRequestMethod("POST");
            }
            if (param != null) {
                outputStream = con.getOutputStream();
                outputStream.write(param.getBytes());
                outputStream.flush();
            }
            inputStream = con.getInputStream();//获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = bufferReader.readLine()) != null) {
                result.append(inputLine).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (result.toString().endsWith("\n")) {
            result = new StringBuilder(result.toString().replace("\n", ""));
        }
        return result.toString();
    }

    static X509TrustManager xtm = new X509TrustManager() {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
        }
    };

}
