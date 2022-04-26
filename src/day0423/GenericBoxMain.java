package day0423;

public class GenericBoxMain {
    public static void main(String[] args) {
        GenericBox<String> genericBox = new GenericBox<>();
        genericBox.set("kim");
        String str = genericBox.get();
        System.out.println(str.toUpperCase());

        GenericBox<Integer> intBox = new GenericBox<>();
        intBox.set(new Integer(5));   // 컴파일 오류 발생
        Integer intValue = intBox.get();
        System.out.println(intValue);
    }
}
