import com.sun.org.apache.xpath.internal.operations.Bool

fun main(args: Array<out String>){
    val user = User(0, null);
    println(user)

    HelloKotlin::class.constructors.map { println(it)}

    /*var a = 8
    var b = 2

    println(plus(a, b))
    println(sub(a, b))
    println(mutl(a, b))
    println(devide(a, b))*/

    println(sayHello("张三"))
}
class HelloKotlin{
    fun hello(){

    }
}

fun plus (a:Int, b:Int):Int{
    return a+b
}
fun sub(a:Int, b:Int):Int{
    return a-b
}
fun mutl(a:Int, b:Int):Int{
    return a*b
}
fun devide(a:Int, b:Int):Int{
    return a/b
}
//作业
//sayHello
fun sayHello(name:String):String{
    return "$name hello!"
}
//checkAge
fun checkAge(age:Int): Boolean{
    var flag = false
    if (age>18){
        flag = true
    }else if (age < 18){
        flag = false
    }
    return flag;
}
//saveLog
fun saveLog(logLevel:Int):Unit{
    if (logLevel>3){
        println("save log")
    }
}
