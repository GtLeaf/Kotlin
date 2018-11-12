package ClassPractice

/*
* 程序员A创建的洗衣机
* */
class WashMachine(var module:String, var size:Int) {

    var isDoorOpen = false
    var mode = 0

    fun openDoor(){
        println("洗衣机的门打开了...")
        isDoorOpen = true
    }
    fun closeDoor(){
        println("洗衣机的门关闭了...")
        isDoorOpen = false
    }
    fun start(){
        if (isDoorOpen){
            println("哔哩哔哩...请先关门")
            return
        }
        when(mode){
            0 -> { println("没有选择模式，不能开始洗衣服")}
            1 -> {
                println("正在放水...水满了。")
                println("轻揉开始，电机转速 慢")
                setMotorSpeed(100)
                println("衣服洗好了")
            }
            2 -> {
                println("正在放水...水满了。")
                println("狂揉开始，电机转速 快")
                setMotorSpeed(1000)
                println("衣服洗好了")
            }
            else -> {
                println("模式不能被识别")
            }
        }
    }

    fun selectMode(mode:Int){
        this.mode = mode
        when(mode){
            0 -> println("初始模式，请您选择模式")
            1 -> println("轻揉")
            2 -> println("狂揉")
            else -> println("不要乱拧，拧坏了不保修啊")
        }
    }

   private fun setMotorSpeed(speed:Int){
        println("发动机转速${speed}圈/秒")
    }
}