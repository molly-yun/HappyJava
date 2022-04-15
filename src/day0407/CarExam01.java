package day0407;

public class CarExam01 {
    public static void main(String[] args) {
        Bus b1 = new Bus();
        b1.run();
        //b1.안내방송();

        Car c1 = new Bus(); // 버스는 자동차다. 하지만 '자동차는 버스다'라는 말은 성립하지 않는다.
        c1.run();   // 부모가 가진 메소드를 자식이 오버라이딩하면 자식 메소드로 실행된다. Bus 클래스의 오버라이딩 run 메소드 실행
        //c1.안내방송();    // 자동차이기 때문에 버스가 가지고 있는 안내방송 기능이 없다.
        Bus b2 = (Bus)c1;   // c1이 참조하는 것을 Bus타입으로 바꿔서(형변환) b2도 참조하라는 뜻. c1이 참조하는 타입은 Car인데 실제 인스턴스는 Bus이기 때문에 가능한 것
        // c1의 인스턴스가 Bus가 아니라면 Bus로 형변환을 할 수 없다.
        b2.안내방송();

        Car c2 = new SuperCar();    // 슈퍼카는 자동차다(O) 자동차는 슈퍼카다(X)
        c2.run();   // SuperCar 클래스의 오버라이딩 run 메소드 실행. 그(c2) 자동차는 달린다.
    }
}
