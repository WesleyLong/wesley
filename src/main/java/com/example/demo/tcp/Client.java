package com.example.demo.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        //1.连接到指定的服务器(ip+port)
        Socket socket = new Socket("127.0.0.1",8899);
        System.out.println("已连接成功");
        //2.获取写出流
        OutputStream out = socket.getOutputStream();
        //3.写出数据,字节流只能写出整数或字节数组
        //将hello对应整数编程对应的字节数组，getBytes()将String转换为byte[]
        out.write("hello".getBytes());
        System.out.println("客户端成功发送数据");
        InputStream in = socket.getInputStream();
        for (int i = 0;i < 5;i++){
            char data = (char)in.read();
            System.out.print(data);
        }
        System.out.println();
        System.out.println("成功接收服务器端数据");
        out.close();
    }
}
