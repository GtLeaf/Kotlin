
//变量的声明和使用
fun main(args: Array<String>) {
    var name = "张三"
    name = "李四"
    //显示声明
    var i:Int = 8
    var a:Boolean
    println(name)

    //变量的取值范围
    /*val aByte:Byte = Byte.MAX_VALUE
    val bByte:Byte = Byte.MIN_VALUE

    println("Byte最大值"+aByte)
    println("Byte最小值"+bByte)

    val aLong:Long = Long.MAX_VALUE
    val bLong:Long = Long.MIN_VALUE

    println("Byte最大值"+aLong)
    println("Byte最小值"+bLong)*/

    var num = Math.sqrt(5.0) - Math.sqrt(4.0)
    var num2 = Math.sqrt(4.0) - Math.sqrt(3.0)
    println(num < num2)

    var num3 = Math.pow(2.0, 100.0)
    var num4 = Math.pow(3.0, 75.0)
    println(num3 < num4)
    println("num3="+num3)
    println("num4="+num4)
}