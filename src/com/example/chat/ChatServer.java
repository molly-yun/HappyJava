package com.example.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999); // 1. serverSocket은 9999 Port에서 클라이언트의 접속을 대기한다.

        List<PrintWriter> outList = Collections.synchronizedList(new ArrayList<>());
        // 6. 모든 클라이언트에게 메시지 전송 처리를 위해 outList라는 공유 객체를 만들어 thread가 새로 만들어질 때마다 리스트에 추가되도록 한다.

        while (true) {  // 5. 다수의 클라이언트와 통신하기 위해 클라이언트를 받아들이고 thread 처리해주는 작업을 반복한다.
            Socket socket = serverSocket.accept();  // 2. 클라이언트가 접속하면(accept, 클라이언트를 받아들임) 클라이언트와 통신하기 위한 socket 을 반환한다.
            System.out.println("접속:" + socket);

            ChatThread chatThread = new ChatThread(socket, outList);
            // 3. 접속한 클라이언트와의 통신을 위한 Thread 처리가 필요하므로 Thread를 상속받는 클래스(ChatThread)를 만든 뒤 run() 메소드를 오버라이딩한다.
            // 4. 또 클라이언트와 통신하기 위한 socket을 넘겨주어야 하므로 ChatThread 클래스에서 Socket을 인자로 받는 생성자를 만든다.
            chatThread.start(); // 4-1. 여기까지 하면 하나의 클라이언트와의 통신을 담당하는 chatThread 처리 작업이 완료된다.
        }
    }
}

class ChatThread extends Thread {
    private Socket socket;
    private List<PrintWriter> outList;
    private PrintWriter out;
    private BufferedReader in;

    // 하나의 클라이언트에 하나의 Thread를 배정한다.
    public ChatThread(Socket socket, List<PrintWriter> outList) {
        this.socket = socket;
        this.outList = outList;

        try {
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 1) socket에 쓰기 위한 객체를 얻는다.
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 2) socket으로부터 읽어들일 수 있는 객체를 얻는다.

            outList.add(out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void run(){
        String line = null;

        // 3) 서버는 특정 클라이언트가 보낸 메시지를 읽는다.
        // 4) 서버는 접속한 모든 클라이언트에게 읽어들인 메시지를 보낸다.
        try {
            while((line = in.readLine()) != null) {
                for (int i = 0; i < outList.size(); i++) {  // 접속한 모든 클라이언트에게 메시지를 전송한다.
                    PrintWriter o = outList.get(i);
                    o.println(line);
                    o.flush();  // * flush 처리를 해주어야 서버로 전송이 된다.
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally { // 접속 연결이 끊어질 경우 무조건 처리되는 부분
            try {
                outList.remove(out);    // 접속이 끊어진 클라이언트는 outList 공유객체에서 제외한다.
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            for (int i = 0; i < outList.size(); i++) {  // 접속한 모든 클라이언트에게 메시지를 전송한다.
                PrintWriter o = outList.get(i);
                o.println("어떤 클라이언트가 접속이 끊어졌어요.");
                o.flush();  // * flush 처리를 해주어야 서버로 전송이 된다.
            }
            try {
                socket.close(); // 접속이 끊어진 클라이언트의 socket을 닫는다.
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
