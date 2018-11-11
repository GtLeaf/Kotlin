import java.math.BigInteger

fun main(args: Array<String>) {
    /*println("输入第一个数字")
    var num1Str = readLine()
    println("输入第二个数字")
    var num2Str = readLine()

    try{
        var num1:Int = num1Str!!.toInt()
        var num2:Int = num2Str!!.toInt()
    }catch (e:Exception){
        println("请输入数字，不要输入其他字符")
    }
    println("${num1} + ${num2} = ${num1+num2}")*/

    /*var num = BigInteger("60")
    println(fact(num))*/

    println(ollAll2(100000, 0))
}

//计算阶乘
fun fact(num:BigInteger):BigInteger{
    if (BigInteger.ONE == num){
        return BigInteger.ONE
    }else{
        return num*fact(num- BigInteger.ONE)
    }
}
//累加操作
fun ollAll(num:Int):Int{
    println("计算机第${num}次运算")
    if(1 == num){
        return 1
    }else{
        return num+ollAll(num-1);
    }
}

//尾递归优化的累加操作tailrec
tailrec fun ollAll2(num:Int, result:Int):Int{
    println("计算机第${num}次运算")
    if(0 == num){
        return result
    }else{
        return ollAll2(num-1, result+num);
    }
}