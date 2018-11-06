class DiaryTools2 {

    fun diaryGenerater(placeName:String):String{
        return """
今天天气晴朗，万里无云，我们去${placeName}游玩，首先映入眼帘的是，
${placeName}${numToChinese(placeName.length)}个鎏金大字。
"""
    }
    fun numToChinese(num:Int):String{
        var result = when(num){
            1 -> "一"
            2 -> "二"
            3 -> "三"
            4 -> "四"
            5 -> "五"
            else -> "地名太长了，我记不清了"
        }
        return result
    }
}

//默认参数
val Pi = 3.14159f
fun 获取圆的周长(PI:Float = Pi, 半径:Float):Float{
    return 2*PI*半径
}
fun 获取圆的周长2(直径:Float, PI:Float = Pi ):Float{
    return PI*直径
}

fun main(args: Array<String>) {
    /*var diaryTools2 = DiaryTools2()
    println(diaryTools2.diaryGenerater("颐和园"))*/
    //函数式表达式
    /*var i = {x:Int, y:Int -> x+y}
    var j:(Int, Int)->Int = {x,y -> x+y}
    println(add(3,5))
    println(i(3,4))
    println(j(2,4))*/

    println(获取圆的周长(3.1415f, 2.0f))
    println(获取圆的周长(半径 = 2.0f))
    println(获取圆的周长2(4.0f))
}

fun add (x:Int, y:Int):Int = x+y

//字符串到数字转换
fun StringToInt(){
    var a = 13
    var b = "String"
    b = a.toString()
}

