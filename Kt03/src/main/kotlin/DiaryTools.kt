class DiaryTools {

    fun diaryGenerater(placeName:String):String{
        return """
            今天天气晴朗，万里无云，我们去${placeName}游玩，首先映入眼帘的是，
            ${placeName}${placeName.length}个鎏金大字。
            """
    }

    fun returnBig(a:Int, b:Int):Int{
        if(a>b) return a else return b
    }

    fun complare(str1:String, str2:String):Boolean{
        return str1 == str2
//        return str1.equals(str2)
    }
}

fun main(args: Array<String>) {
    /*var diray = DiaryTools();
    println(diray.diaryGenerater("人民公园"))
    var a = 3
    var b = 5
    println("较大那个数的值为：${diray.returnBig(a, b)}")*/
    /*var str1 = "Andy"
    var str2 = "andy"
     println(str1.equals(str2, true))//为true时不区分大小写*/
    println(heat(null))
}

//接收一个非空的String,加上“?”表示参数可以为null
fun heat(str:String):String{
    return "热${str}"
}