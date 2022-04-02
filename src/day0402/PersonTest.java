package day0402;

public class PersonTest {
    public static void main(String[] args) {
        //day0402.Person p1;  // 참조값을 정하지 않았으므로 null
        Person p1 = new Person();
        Person p2 = new Person();
        p1.name = "홍길동";    // String은 new 연산자 없이 인스턴스를 사용할 수 있으므로 되도록이면 new 연산자는 사용하지 말자
        p1.address = "일산";
        p1.isVip = true;
        p2.name = "조조";
        p2.address = "서울";

        System.out.println(p1.name);
        System.out.println(p1.name.length());
        System.out.println(p1.address);
        System.out.println(p1.address.length());  // null 값은 길이가 없음
        System.out.println(p1.isVip);
        System.out.println("---------------");
        System.out.println(p2.name);
        System.out.println(p2.name.length());
        System.out.println(p2.address);
        System.out.println(p2.isVip);


    }
}
