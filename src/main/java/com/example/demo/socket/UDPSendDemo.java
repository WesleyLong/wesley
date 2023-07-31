package com.example.demo.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSendDemo {
    //注意事项:udp协议通信，要保证数据不丢失，要先运行接收端代码块
    public static void main(String[] args) {
        try {
            //创建DatagramSocket类对象，此类表示用来发送和接收数据报包的套接字。
            DatagramSocket ds = new DatagramSocket();
            //创建要发送的数据，并将数据打包
            byte[] data = {
                    (byte) 0xf5, (byte) 0xc1, (byte) 0x28, (byte) 0x06, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0xd0, (byte) 0x00, (byte) 0x11, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                    (byte) 0x44, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x34, (byte) 0x42, (byte) 0x45, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42,
                    (byte) 0x46, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x34, (byte) 0x42, (byte) 0x47, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42,
                    (byte) 0x48, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x1c, (byte) 0x42, (byte) 0x49, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42,
                    (byte) 0x4a, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42, (byte) 0x4b, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42,
                    (byte) 0x4c, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x34, (byte) 0x42, (byte) 0x4d, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42,
                    (byte) 0x4e, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42, (byte) 0x4f, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x34, (byte) 0x42,
                    (byte) 0x50, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42, (byte) 0x51, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42,
                    (byte) 0x52, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42, (byte) 0x53, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x34, (byte) 0x42,
                    (byte) 0x54, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x34, (byte) 0x42, (byte) 0x55, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x34, (byte) 0x42,
                    (byte) 0x56, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x3c, (byte) 0x42, (byte) 0x57, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x34, (byte) 0x42,
                    (byte) 0x58, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x34, (byte) 0x42, (byte) 0x59, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42,
                    (byte) 0x5a, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42, (byte) 0x5b, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42,
                    (byte) 0x5c, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x3c, (byte) 0x42, (byte) 0x5d, (byte) 0x17, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x38, (byte) 0x42
            };
            //DatagramPacket类表示数据报包，用来实现无连接包投递服务。
            //DatagramPacket(byte[] buf, int offset, int length, SocketAddress address)
            //该构造方法表示 构造数据报包
            //用来将长度为 length 偏移量为 offset 的包发送到指定主机上的指定端口号。
            int i = 0;
            while (i < 10) {
                DatagramPacket dp = new DatagramPacket(data, 0, data.length, InetAddress.getByName("10.40.92.114"), 9999);
                //socket对象通过send方法发送数据报包dp
                ds.send(dp);
                //关闭资源
                i++;
            }
            ds.close();
            //捕获错误
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}