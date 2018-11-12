package ClassPractice

class Rect(var height:Int, var width:Int)
class Girl(var chactor:String, var vioce:String){
    fun smaile(){
        println("很甜的笑了")
    }
    fun cry(){

    }
}

fun main(args: Array<String>) {
    var rect1 = Rect(20, 10);
    var girl = Girl("剽悍", "甜美")
    println("矩形的高度、宽度为:${rect1.height}、${rect1.width}")
}