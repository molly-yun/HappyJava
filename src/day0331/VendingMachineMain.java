package day0331;

public class VendingMachineMain {
    public static void main(String[] args) {
        VendingMachine.printVersion();
        VendingMachine vm1 = new VendingMachine();
        // vm1.printVersion(); 실행되는 데 문제는 없지만 static 메소드는 클래스를 통해 호출하는 것이 바람직하다.
        // static 메소드는 이탤릭체로 표시가 된다.
        // 클래스를 통해 직접 호출해야 static 메소드인 것을 한 눈에 확인 가능하므로 좋다.
        VendingMachine vm2 = new VendingMachine();

        String product = vm1.pushProductButton(1000);   // 반환값이 있는 메소드는 참조변수를 통해 값을 받아와야 한다.
        System.out.println(product);
        String product2 = vm2.pushProductButton(1500);
        System.out.println(product2);

    }
}
