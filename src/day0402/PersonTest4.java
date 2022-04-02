package day0402;

public class PersonTest4 {
    static int i;
    static {
        i = 500;
        System.out.println("static block"); // static block이 main메소드보다 먼저 출력된다.
    }

    public static void main(String[] args) {
        System.out.println("Hello");
    }
}

// javac day0402.PersonTest4.java
// java day0402.PersonTest4

