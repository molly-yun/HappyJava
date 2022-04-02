package day0402;

public class Person {
    String name;
    String address;
    boolean isVip;
    static int count = 0;
    static {    // 클래스 필드는 static 블록에서도 초기화 할 수 있다.
        count = 100;
    }

    public void printName() {
        System.out.println("내 이름은 " + name);
    }

    public static void printCount() {
        System.out.println("count: " + count);
    }
}
