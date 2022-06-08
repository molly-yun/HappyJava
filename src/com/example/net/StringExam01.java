package com.example.net;

public class StringExam01 {
    public static void main(String[] args) {
        String firstLine1 = "GET / HTTP/1.1";
        String firstLine2 = "GET /index.html HTTP/1.1";
        String firstLine3 = "GET /images/i.jpg HTTP/1.1";

        firstLine1 = firstLine1.substring(4, firstLine1.lastIndexOf(" "));
        firstLine2 = firstLine2.substring(4, firstLine2.lastIndexOf(" "));
        firstLine3 = firstLine3.substring(4, firstLine3.lastIndexOf(" "));
        System.out.println("*" + firstLine1 + "*");
        System.out.println("*" + firstLine2 + "*");
        System.out.println("*" + firstLine3 + "*");
    }
}
