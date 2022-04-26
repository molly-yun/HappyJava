package day0423;

public class GenericBox<T> {
    private T t;

    public void set(T obj) {
        this.t = obj;
    }

    public T get() {
        return this.t;
    }
}
