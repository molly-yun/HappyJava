package com.example.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class VerySimpleWebServer {
    public static void main(String[] args) throws Exception{
        // 9090 port로 대기한다.
        ServerSocket ss = new ServerSocket(9090);

        // 클라이언트를 대기한다.
        // 클라이언트가 접속하는 순간, 클라이언트와 통신할 수 있는 socket을 반환한다.
        // 첫번째로 브라우저에서 http://127.0.0.1:9090/ 입력한다.
        // 두번째로 http://127.0.0.1:9090/board/hello.html/ 입력한다.
        System.out.println("클라이언트 접속을 기다립니다.");
        // Socket은 브라우저(client)와 통신할 수 있는 객체이다.
        Socket socket = ss.accept();

        // client와 읽고 쓸 수 있는 InputStream, OutputStream을 반환한다.
        OutputStream out = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
        InputStream in = socket.getInputStream();

        // HTTP 프로토콜은 클라이언트가 서버로 정보를 보내준다. (요청정보)
        /**
         * 한줄한줄 읽어들이기 전 처음 작성한 코드
         byte[] buffer = new byte[512];  // 읽어들이기 위한 변수
         int readCount = 0;  // 읽어들인 건수

         // 읽어들일 것이 없을 때까지 읽어들인다는 뜻
         while ((readCount = in.read(buffer)) != -1) {
         System.out.write(buffer, 0, readCount); // write는 byte 배열을 출력할 수 있다.
         }

         System.out.println(socket.toString());
         **/
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String firstLine = br.readLine();
        List<String> headers = new ArrayList<>();
        String line = null;

        // 빈 줄을 만나면 while문을 끝낸다.
        while (!(line = br.readLine()).equals("")) {
            headers.add(line);
        }

        // 요청정보 읽기 끝
        System.out.println(firstLine);
        for (int i = 0; i < headers.size(); i++) {
            System.out.println(headers.get(i));
        }

        // 서버에게 응답메시지 보내기
        // HTTP/1.1 200 OK <- 상태메시지
        // 헤더1
        // 헤더2
        // 빈 줄
        // 브라우저에 표시해줄 내용
        pw.println("HTTP/1.1 200 OK");
        pw.println("name: kim");
        pw.println("email: mollyyun91@gmail.com");
        pw.println();
        pw.println("<html>");
        pw.println("<h1>Hello!!!</h1>");
        pw.println("</html>");
        pw.close();

        ss.close();
        System.out.println("서버가 종료됩니다.");
    }
}
