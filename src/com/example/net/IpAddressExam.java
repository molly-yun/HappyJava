package com.example.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpAddressExam {
    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getLocalHost(); // 내 컴퓨터의 IP정보를 구한다. getLocalHost는 상위에 (checked)Exception을 상속받으므로 try-catch 처리를 해주어야 한다.
            System.out.println(ia.getHostAddress());
        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        }

        try {
            InetAddress[] iaArray = InetAddress.getAllByName("www.google.com");
            // google의 IP정보를 구한다. 하나의 도메인은 여러개의 IP를 가질 수도 있기 때문에 InetAddress를 배열로 선언한다.
            for (InetAddress ia : iaArray) {
                System.out.println(ia.getHostAddress());
            }
        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        }
    }
}
