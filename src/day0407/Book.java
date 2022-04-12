package day0407;

public class Book {
    //public int price;
    private int price;  // 정보은닉을 위해 private 접근제어자 설정
    private String name;

    // private으로 선언된 필드는 외부에서 함부로 접근할 수 없다. 필드의 값을 수정하고 얻기 위한 getter&setter 메소드를 만든다.
    // getter&setter 메소드를 '프로퍼티'라고 한다. 어떠한 필드에 대한 프로퍼티인지에 따라 이름이 바뀐다. price프로퍼티, name프로퍼티... (필드명 프로퍼티)
    public int getPrice() {
        //return price;
        return this.price * 2; // 또 getter&setter를 사용하면 계산식도 적용 가능하다.
        // 메소드가 길어지면 자신이 가지고 있는 필드를 나타내는 것인지, 메소드 안에 선언된 변수를 나타내는 것인지 헷갈릴 수 있으므로
        // this.필드명 으로 구분지어준다. this: 나 자신의 인스턴스를 뜻한다.
        // this 예약어는 static메소드에서 사용하지 못한다. static메소드는 인스턴스 없이도 사용 가능한 메소드이기 때문에
    }

    public void setPrice(int price) {   // 괄호 안의 int price는 지역변수
        this.price = price; // 외부로부터 지역변수 price의 값을 받아와서 Book클래스의 private int price 필드로 넘겨준다.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
