package designpattern.proxy.hello;

public class ProxyDemo {

    public static void main(String[] args) {

        IHello hello = new Hello();

        /** 動態代理指定類 **/
        IHello proxy1 = new HelloProxy(hello).getHello();
        /** 動態代理寫法 **/
        IHello proxy2 = new DynamicProxy<>(hello).getProxyObj();
        proxy2.hello();
    }
}
