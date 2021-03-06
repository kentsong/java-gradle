package designpattern.proxy.hello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy<T> {

    private T t;

    public DynamicProxy(T t) {
        this.t = t;
    }

    //使用Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)返回某个对象的代理对象

    /**
     * ClassLoader loader:Java类加载器; 可以通过这个类型的加载器，在程序运行时，将生成的代理类加载到JVM即Java虚拟机中，以便运行时需要！
     * Class<?>[] interfaces:被代理类的所有接口信息; 便于生成的代理类可以具有代理类接口中的所有方法
     * InvocationHandler h:调用处理器; 调用实现了InvocationHandler 类的一个回调方法
     */
    @SuppressWarnings("unchecked")
    public T getProxyObj() {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(), t.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * InvocationHandler接口只定义了一个invoke方法，因此对于这样的接口，我们不用单独去定义一个类来实现该接口，
             * 而是直接使用一个匿名内部类来实现该接口，new InvocationHandler() {}就是针对InvocationHandler接口的匿名实现类
             */
            /**
             * 在invoke方法编码指定返回的代理对象干的工作
             * proxy : 把代理对象自己传递进来
             * method：把代理对象当前调用的方法传递进来
             * args:把方法参数传递进来
             *
             * 当调用代理对象的star.sing("逍遥叹");或者 star.act("琅琊榜")方法时，
             * 实际上执行的都是invoke方法里面的代码，
             * 因此我们可以在invoke方法中使用method.getName()就可以知道当前调用的是代理对象的哪个方法
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //在原方法前边动态插入代码
                System.out.println("proxy point ===> pre invoke " + method.getName());
                Object result = method.invoke(t, args);
                //在原方法后边动态插入代码
                System.out.println("proxy point ===> after invoke " + method.getName());
                return result;
            }
        });
    }


}
