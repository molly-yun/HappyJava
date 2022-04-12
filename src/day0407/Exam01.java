package day0407;

public class Exam01 {
    public static void main(String[] args) {
        Parent p1 = new Parent();
        //System.out.println(p1.i); 정보은닉 관점에서 필드를 직접 참조하는 것은 안된다. private제어자와 getter&setter 사용하는 이유
        p1.printI();
        System.out.println("------------------");
        Child c1 = new Child();
        //System.out.println(c1.i);
        c1.printI();
        System.out.println("------------------");
        Parent p2 = new Child();    // Child는 Parent 후손 또는 자식이다.
        //System.out.println(p2.i);   // 부모타입으로 자손을 참조했을 때, 필드는 오버라이딩 되지 않는다.
        p2.printI();    // 메소드 오버라이딩은 자식 메소드가 실행되고, 필드 오버라이딩은 부모의 필드값이 유지된다.
        p2.printII();   // 부모클래스에 선언된 필드값이 자식의 필드값으로 바뀌게 된다면 예상치못한 결과가 나올 수 있기 때문에
    }
}
