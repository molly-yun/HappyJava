package day0331;

public class VendingMachine {
    // field: 클래스가 가지는 요소들
    // 생성자
    // method
    public String pushProductButton(int menuId) {
        System.out.println(menuId + "를 전달받았습니다.");
        return "콜라";
    }

    // static이 붙은 메소드는 클래스 인스턴스를 생성하지 않고도 실행 가능
    // VendingMachineMain에서 VendingMachine이 사용가능한 객체에 printVersion 메소드만 뜨는 것을 확인할 수 있다.
    public static void printVersion() {
        System.out.println("v1.0");
    }
}
