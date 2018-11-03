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

fun main(args: Array<String>) {
    var diaryTools2 = DiaryTools2()
    println(diaryTools2.diaryGenerater("颐和园"))
}
