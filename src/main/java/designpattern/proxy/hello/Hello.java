package designpattern.proxy.hello;

public class Hello implements IHello {

    @Override
    public void hello() {
        System.out.println("hello world");
    }

    @Override
    public void move() {
        System.out.println("dog is moving");
    }
}
