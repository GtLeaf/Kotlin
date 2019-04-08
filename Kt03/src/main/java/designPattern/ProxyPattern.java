package designPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyPattern {
    public static void main(String[] args){
        Singer singer = new Singer();
        ISinger proxy = (ISinger) Proxy.newProxyInstance(singer.getClass().getClassLoader(),
                Singer.class.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        return method.invoke(singer, args);
                    }
                });
        proxy.sing();
    }
}

interface ISinger{
    void sing();
}

class Singer implements ISinger{

    @Override
    public void sing() {
        System.out.println("唱歌");
    }
}
