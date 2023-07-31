package com.example.demo;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
public class PasswordEncryptor {
    @Resource
    private StringEncryptor encryptor;

    @GetMapping("/getPass")
    public void getPass() {
        f1();
        f2();

    }

    void f1() {
        System.out.println("--------------测试-------------");
        String password = "zerocarbon";
        System.out.println("oracle:" + encryptor.encrypt(password));

        password = "123456";
        System.out.println("redis:" + encryptor.encrypt(password));

        password = "zerocarbon";
        System.out.println("minio:" + encryptor.encrypt(password));

        password = "zerocarbon";
        System.out.println("mysql:" + encryptor.encrypt(password));
        System.out.println("--------------测试-------------");
    }

    void f2() {
        String password = "TPFJK##1234";
        System.out.println("oracle:" + encryptor.encrypt(password));

        password = "cx+123";
        System.out.println("redis:" + encryptor.encrypt(password));

        password = "Aa123456";
        System.out.println("minio:" + encryptor.encrypt(password));

        password = "Ht+1122";
        System.out.println("mysql:" + encryptor.encrypt(password));

    }

    public static void main(String[] args) {
        UUID id = UUID.randomUUID();
        System.out.println(id);
    }
}
