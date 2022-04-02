package day0331;

public class MathBeanTest {
    public static void main(String[] args) {
        MathBean math = new MathBean();

        math.printClassName();
        math.printNumber(5000);
        int r = math.getOne();  // 어떠한 값을 반환하는 메소드는 그 반환값을 받는 변수를 설정해야 한다.
        System.out.println(r);
        int sum = math.plus(74, 68);
        System.out.println(sum);
    }
}
