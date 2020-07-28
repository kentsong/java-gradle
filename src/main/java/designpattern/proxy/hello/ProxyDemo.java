package designpattern.proxy.hello;

public class ProxyDemo {

    public static void main(String[] args) {

        IHello hello = new Hello();

        /** 動態代理指定類 **/
        IHello proxy1 = new HelloProxy(hello).getHello();
        /** 動態代理寫法 **/
        IHello proxy2 = new DynamicProxy<>(hello).getProxyObj();
        System.out.println("代理类产生后，只有在执行时才会 call InvocationHandler.invoke()");

//        proxy1.hello();
//        proxy1.move();
        proxy2.hello();
        proxy2.move();
    }
}
