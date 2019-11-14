package designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloProxy<T> {

    private IHello hello;

    public HelloProxy(IHello hello) {
        this.hello = hello;
    }

    public IHello getHello() {

        return (IHello) Proxy.newProxyInstance(getClass().getClassLoader(), hello.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //在原方法前边动态插入代码
                System.out.println("proxy point ===> pre invoke");
                Object result = method.invoke(hello, args);
                //在原方法后边动态插入代码
                System.out.println("proxy point ===> after invoke");

                return result;
            }
        });
    }


}
