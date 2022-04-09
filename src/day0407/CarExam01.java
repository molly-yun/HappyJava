package day0407;

public class CarExam01 {
    public static void main(String[] args) {
        Bus b1 = new Bus();
        b1.run();
        //b1.안내방송();

        Car c1 = new Bus(); // 버스는 자동차다.
        c1.run();   // 부모가 가진 메소드를 자식이 오버라이딩하면 자식 메소드로 실행된다. Bus 클래스의 오버라이딩 run 메소드 실행
        //c1.안내방송();    // 자동차이기 때문에 버스가 가지고 있는 안내방송 기능이 없다.
        Bus b2 = (Bus)c1;   // c1이 참조하는 타입은 Bus인데 이것을 Bus클래스타입으로 b2가 다시 참조한다는 뜻. 이 경우 형변환 반드시 해주어야 함
        b2.안내방송();

        Car c2 = new SuperCar();
        c2.run();   // SuperCar 클래스의 오버라이딩 run 메소드 실행. 그(c2) 자동차는 달린다.
    }
}
