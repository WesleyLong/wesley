package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DecimalFormat;

/**
 * @author wang yaowen
 * @version 1.0
 * @date 2020/5/31 下午12:34
 */

@Component
public class Test7 {
    public static void main(String[] args) {
        //依然创建DatagramSocket类对象，因为此类表示用来发送和接收数据报包的套接字。
        //为了让捕获异常后的try代码块的finally可调用对象ds，故先创建对象，下面再建立实例
        try (DatagramSocket ds = new DatagramSocket(10000)) {
            while (true) {
                //创建数据报套接字并将其绑定到本地主机上的指定端口10000（对应发送端创建的端口）
                //创建字节数组以做数据缓冲区
                byte[] words = new byte[1024];
                //创建DatagramPacket类对象，并调用构造器用来接收长度为 length 的数据包
                DatagramPacket dp = new DatagramPacket(words, 0, words.length);
                //调用DatagramSocket类方法receive()接收数据报包
                ds.receive(dp);
                //再将数据报包转换成字节数组
                byte[] data = dp.getData();
                Test6 t = new Test6();
                t.setUdpData(data);
                t.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

