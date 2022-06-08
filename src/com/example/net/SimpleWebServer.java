package com.example.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleWebServer {
    public static void main(String[] args) throws Exception{
        // 1. 서버는 클라이언트를 기다린다.
        // 2. 클라이언트가 접속하면 해당 클라이언트와 통신할 수 있는 Socket이 반환된다.
        //  - Socket이 반환되면 클라이언트와 통신하는 Thread를 만들어서 작업을 처리하게 된다.
        // 1번부터 다시 시작한다.

        ServerSocket ss = new ServerSocket(9090);

        System.out.println("server start!!!");
        while (true) {
            Socket socket = ss.accept();    // accept 하면 대기 상태. 여기에서 Socket이 반환된다.
            System.out.println("client: " + socket);
            SocketThread st = new SocketThread(socket); // accept로 리턴한 socket을 넘겨준다.
            st.start(); // start로 thread의 흐름이 시작되고 난 뒤 다시 while문 처음부터 반복한다.

        }

        //ss.close();   위의 while문이 무한루프라서 ss.close() 코드까지 도달할 수 없다는 에러가 뜬다.
    }
}

class SocketThread extends Thread {
    Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    // thread를 상속받으면 run메소드를 오버라이딩해주어야 한다.
    // 브라우저의 요청을 처리하는 메소드
    public void run() {
        System.out.println("socket을 이용해 client와 동작한다.");

        try {
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));  // 한 줄씩 socket을 쓰는 객체
            BufferedReader br = new BufferedReader(new InputStreamReader(in));  // 한 줄씩 socket에서 읽는 객체

            // client가 접속하면 브라우저의 요청을 읽어들여서 그 결과를 반환한다.
            Request request = processRequest(in, br);
            System.out.println(request.getFirstLine());
            System.out.println(request.getPath());

            // 응답한다. path의 내용을 읽어서 클라이언트에게 전송한다.
            processResponse(request.getPath(), out, pw);

            socket.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    } // run

    private void processResponse(String path, OutputStream out, PrintWriter pw) throws Exception {
        String baseDir = "D:/HappyJava/src/com/example/net";
        String readFile = baseDir + path;
        //FileReader fr = new FileReader(readFile);
        //BufferedReader br = new BufferedReader(fr);
        FileInputStream fis = new FileInputStream(readFile);

        pw.println("HTTP/1.1 200 OK");
        pw.println("name: kim");
        pw.println("email: mollyyun91@gmail.com");
        pw.println();
        //pw.println("<html><h1>hello</h1></html>");
        pw.flush(); // pw.close(); 하면 Stream 자체가 닫히기 때문에 아래의 OutputStream 부분이 실행이 안된다.

//        String line = null;
//        while ((line = br.readLine()) != null) {
//            pw.println(line);
//        }
        byte[] buffer = new byte[512];
        int readCount = -1;
        while ((readCount = fis.read(buffer)) != -1) {
            out.write(buffer, 0, readCount);
        }

        //pw.close();
        out.close();
    }

    private Request processRequest(InputStream in, BufferedReader br) throws Exception {
        Request request = new Request();
        request.setFirstLine(br.readLine());    // GET /(사용자가 요청하는 정보) HTTP/1.1
        String line = null;
        while (!(line = br.readLine()).equals("")) {    // 빈 줄이 나오고 header 정보 끝
            request.addHeader(line);
        }
        return request;
    }
}

class Request {
    String firstLine;   // GET /
    List<String> headers;
    String path;

    public Request() {
        headers = new ArrayList<>();   // headers
    }

    public String getFirstLine() {
        return firstLine;
    }

    public String getPath() {
        return path;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
        this.path = firstLine.substring(4, firstLine.lastIndexOf(" "));
        if ("/".equals(path)) {
            path = "/index.html";    // /는 보통 index파일을 읽어들이도록 설정한다.
        }
    }

    public void addHeader(String header) {
        headers.add(header);
    }

    public Iterator<String> getHeaders() {
        return headers.iterator();
    }

}