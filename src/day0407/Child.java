package day0407;

public class Child extends Parent {
    public int i = 15;  // Parent 필드 오버라이딩

    public void printI() {  // Parent 메소드 오버라이딩
        System.out.println("child - printI() : " + i);
    }
}
