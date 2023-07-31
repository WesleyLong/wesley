package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author wang yaowen
 * @version 1.0
 * @date 2020/5/31 下午12:34
 */

@Component
public class Test6 implements Runnable {
    private byte[] udpData;

    public void setUdpData(byte[] udpData) {
        this.udpData = udpData;
    }

    @Override
    public void run() {

        //判断报文是否为0X05
        if ((udpData[10] == 0X05)) {
            // 根据实际抓包信息来看，0X05报文比较多，出现的几率比较大，但是这类报文厂家没有定义，所以这里直接首先处理此类报文，如果该类报文，直接什么也不处理
            System.out.println("未定义报文0X05");
        } else {
            // 处理模拟量数据
            //判断报文头部是否为模拟量
            if ((udpData[10] == 0X01) | (udpData[10] == 0X11)) {
                byte[] pidLen = {udpData[9], udpData[8]};
                ByteArrayInputStream byteArrayInputStreamLen = new ByteArrayInputStream(pidLen);
                DataInputStream dataInputStreamLen = new DataInputStream(byteArrayInputStreamLen);

                int j = 0;
                try {
                    j = dataInputStreamLen.readUnsignedShort();
                    j = j / 8;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < j; i++) {
                    //读取DCS测点编号
                    byte[] pidByte = {udpData[17 + i * 8], udpData[16 + i * 8]};
                    ByteArrayInputStream pidByteArrayInputStream = new ByteArrayInputStream(pidByte);
                    DataInputStream pidDataInputStream = new DataInputStream(pidByteArrayInputStream);
                    try {
                        //传到定义的点中
                        int pointId = pidDataInputStream.readUnsignedShort();
                        if (pointId == 172 | pointId == 176) {//172：发电机有功电度 176：厂高变高压侧有功电度
                            System.out.println("测点编码：" + pointId);
                        } else {
                            continue;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //读取DCS测点的测量值
                    byte[] bytetemp = {udpData[23 + i * 8], udpData[22 + i * 8], udpData[21 + i * 8], udpData[20 + i * 8]};
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytetemp);
                    DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                    try {
                        String pointValue = new DecimalFormat("0.000").format(dataInputStream.readFloat());
                        System.out.println("测量值：" + pointValue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}

