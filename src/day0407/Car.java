package day0407;

public class Car {
    public void run() {
        System.out.println("전륜구동으로 달리다.");
    }

    // toString() 메소드를 오버라이딩하지 않으면 인스턴스의 메모리주소값이 출력되는데 일반적으로 이 값은 쓸모없는 값이기 때문
    @Override
    public String toString() {
        return "자동차!!";
    }
}
