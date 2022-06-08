package com.example.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

public class UserUI {
    private BufferedReader br;  // 키보드로부터 입력받으므로 BufferedReader 사용

    public UserUI() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public int menu() {
        System.out.println("1. 회원등록");
        System.out.println("2. 회원목록보기");
        System.out.println("3. 회원정보 수정하기");
        System.out.println("4. 회원삭제");
        System.out.println("5. 종료");

        int menuId = -1;

        try {
            String line = br.readLine();
            menuId = Integer.parseInt(line);    // 입력받은 메뉴번호를 정수로 변환
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return menuId;
    }

    public User regUser() {
        try{
            System.out.println("email을 입력하세요.");
            String email = br.readLine();
            System.out.println("이름을 입력하세요.");
            String name = br.readLine();
            System.out.println("생년을 입력하세요.");
            String strBirthYear = br.readLine();
            int birthYear = Integer.parseInt(strBirthYear);

            User user = new User(email, name, birthYear);

            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void printUserList(List<User> users) {
        System.out.println("email         이름         생년");
        System.out.println("==============================");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.print(user.getEmail());
            System.out.print("         ");
            System.out.print(user.getName());
            System.out.print("         ");
            System.out.print(user.getBirthYear());
            System.out.println();
        }
    }

//    public User updateUser(List<User> users) {
//        try {
//            System.out.println("수정할 회원의 email을 입력하세요.");
//            String regEmail = br.readLine();
//            boolean status = false;
//
//            for (int i = 0; i < users.size(); i++) {
//                //User user = users.get(i);
//                if (regEmail.equals(users.get(i).getEmail())) {
//                    System.out.println("email을 입력하세요.");
//                    String email = br.readLine();
//                    System.out.println("이름을 입력하세요.");
//                    String name = br.readLine();
//                    System.out.println("생년을 입력하세요.");
//                    String strBirthYear = br.readLine();
//                    int birthYear = Integer.parseInt(strBirthYear);
//
//                    User user = new User(email, name, birthYear);
//
//                    return user;
//                } else {
//                    System.out.println("수정할 회원정보가 없습니다.");
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

}
