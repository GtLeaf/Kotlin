package designPattern.proxyPattern

import java.lang.reflect.Proxy


interface ISinger{
    fun sing()
    fun dance(dance:String)
}

class Singer : ISinger{
    override fun dance(dance:String) {
        println("跳$dance")
    }

    override fun sing() {
        println("singing《My Heart Will Go On》")
    }
}

class ProxySinger1(private val singer: Singer) : ISinger{
    override fun dance(dance:String) {

    }

    //对唱歌功能的拓展
    override fun sing() {
        println("hello everyone!")
        singer.sing()
        println("Thanks for listening")
    }
}

//ClassLoader loader：指定当前目标对象使用类加载器，写法固定
//Class<?>[] interfaces：目标对象实现的接口的类型，写法固定
//InvocationHandler h：事件处理接口，需传入一个实现类，一般直接使用匿名内部类

//总结：以上代码只有标黄的部分是需要自己写出，其余部分全都是固定代码。由于java封装了newProxyInstance这个方法的实现细节，
// 所以使用起来才能这么方便，具体的底层原理将会在下一小节说明。
//缺点：可以看出静态代理和JDK代理有一个共同的缺点，就是目标对象必须实现一个或多个接口，加入没有，则可以使用Cglib代理。
class DynamicProxy {
    fun dynamicProxyJDK(){
        val singer = Singer()
        val proxy= Proxy.newProxyInstance(
                singer.javaClass.classLoader,
                Singer::class.java.interfaces
        ) { proxy, method, args ->
            println("Hello Everyone")
            //执行目标对象方法
            val myArg = Array< Any>(10, {})
            val returnValue = method?.invoke(singer, *(args ?: emptyArray())) ?: Unit
            println("Thanks for everyOne")
            returnValue
            //                        return singer
        } as ISinger
        proxy.dance("四小天鹅")
    }
}

//Cglib子类代理工厂



fun main(arg:Array<String>){
    val interfaceProxy = ProxySinger1(Singer())
//    interfaceProxy.sing()

    val dynamicProxy = DynamicProxy()
    dynamicProxy.dynamicProxyJDK()


    /*val singer = Singer()
    val proxy = Proxy.newProxyInstance(singer.javaClass.classLoader,
            Singer::class.java.interfaces
    ) { proxy, method, args -> method.invoke(singer, *args) } as ISinger
    proxy.sing()*/
}
