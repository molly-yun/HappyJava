package com.example.io;

import java.io.*;
import java.util.ArrayList;

public class ObjectInputOutputExam {
    public static void main(String[] args) throws Exception {
        User user1 = new User("hong@example.com", "홍길동", 1992);
        User user2 = new User("go@example.com", "고길동", 1995);
        User user3 = new User("d@example.com", "둘리", 1991);

        ArrayList<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);

//        복사 기능 X
//        ArrayList<User> list2 = list;
//
//        for (int i = 0; i < list2.size(); i++) {
//            System.out.println(list2.get(i));
//        }
//        복사 기능 O
//        ArrayList<User> list2 = new ArrayList<>();
//
//        for (int i = 0; i < list.size(); i++) {
//            list2.add(list.get(i));
//        }
//        복사 여부 확인
//        list.remove(2);
//        System.out.println(list.size());
//        System.out.println(list2.size());

        ArrayList<User> list2 = copy(list);

//        list.remove(2);   // list의 두번째요소를 지워도 list2 에서는 모든 요소가 출력되는 것을 확인할 수 있다.

        for (int i = 0; i < list2.size(); i++) {
            System.out.println(list2.get(i));
        }
    }

    private static ArrayList<User> copy(ArrayList<User> list) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(list);

        out.close();
        bout.close();

        byte[] array = bout.toByteArray();

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(array));
        ArrayList<User> list2 = (ArrayList) in.readObject();
        in.close();
        return list2;
    }
}
