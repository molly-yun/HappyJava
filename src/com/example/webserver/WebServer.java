package com.example.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) throws Exception {
        // 클라이언트가 접속할 때까지 대기할 때 필요한 객체가 ServerSocket
        ServerSocket serverSocket = new ServerSocket(10000);
        System.out.println(1);

        try {
            while (true) {  // 요청을 딱 한번만 주고받고 종료하지 않기 위해 while 반복문을 이용. 이 경우에는 동시에 접속하는 클라이언트의 요청에 응답할 수 없다.
                Socket clientSocket = serverSocket.accept();    // serverSocket은 대기하다가 클라이언트가 접속하면 클라이언트와 통신하는 Socket을 반환한다.
                System.out.println(2);  // serverSocket이 대기중(클라이언트 접속 전)일 때는 이 코드가 실행되지 않는다.

                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        } finally {
            serverSocket.close();
        }
    }
}

class ClientThread extends Thread {
    private Socket clientSocket;
    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() { // Threa run() 메소드는 오버라이딩 해야 한다. run() 메소드는 Exception 발생하지 않으므로 try-catch로 예외처리해준다.
        try {
            InputStream inputStream = clientSocket.getInputStream();    // 클라이언트가 요청하는 정보를 읽어들인다.
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            //System.out.println(br.readLine());
            // http://localhost:10000/hello 경로로 접속한 경우 GET /hello HTTP/1.1 요청정보를 받는다.

            // 클라이언트의 요청에 응답하기 위해서는 반환정보를 출력해야 한다.
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputStream));

            String firstLine = br.readLine();
            String msg = "";
            if (firstLine.indexOf("/hello") >= 0)
                msg = "hello";
            else if (firstLine.indexOf("/hi") >= 0)
                msg = "hi";

            String line = null;
//        line = br.readLine();
//        if (!line.equals(""))   // 여기서 "" 는 빈 줄을 뜻한다.
//            System.out.println(line);   // line이 빈 줄이 아니라면 받은 요청정보를 한 줄 씩 출력한다.
//        line = br.readLine();
//        if (!line.equals(""))
//            System.out.println(line);
            // 위와 같이 한줄한줄 읽고 출력하는 경우 횟수가 반복되면 코드가 쓸데없이 길어지므로 반복문을 이용한다.
            while (!(line = br.readLine()).equals("")) {
                System.out.println(line);
            }
            System.out.println("3: 응답을 완료한다.");

            // 클라이언트가 서버로부터 받는 응답 정보
            // header
            pw.println("HTTP/1.1 200 OK");
            pw.println("name:yun");
            pw.println("email:mollyyun91@gmail.com");
            pw.println();   // 빈 줄
            pw.flush();     // 서버로부터 header 정보를 받은 뒤 그에 해당하는 만큼의 body 정보를 받아들인다.
            // 따라서 header 정보를 먼저 클라이언트로 보내서 클라이언트가 그에 맞게 정보를 받아들인 준비를 하게끔 해준다.

            // body
            // firstLine: GET /abcd HTTP/1.1 형식. 서버는 해당 요청에 대해 /abcd 파일을 읽어서 출력한다.
            // 서버의 입장에서 /abcd 파일이 어느 경로에 있는지 어떻게 확인할까?
            // 요청정보에 이미지파일이 있다면
            pw.println("<html>");
            pw.println(firstLine + " !!!!");
            pw.println("</html>");

            pw.flush(); // 응답정보를 클라이언트로 보낸 뒤 stream과 socket을 close 해야 한다.
            br.close();
            pw.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
