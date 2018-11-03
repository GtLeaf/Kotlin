class WhenDemo {
    fun gradeStudent(score:Int){
        when(score){
            10 -> println("考了满分")
            9 -> println("考了9分")
            8 -> println("考了8分")
            else -> println("需要继续加油！")
        }
    }
}

fun main(args: Array<String>) {
    var whendDemo = WhenDemo()

    whendDemo.gradeStudent(6)
}