package ClassPractice

fun main(args: Array<String>) {
    var washMachine = WashMachine("小天鹅", 12)
    washMachine.openDoor()
    println("我把牛仔裤放进去了")
    washMachine.closeDoor()
    washMachine.selectMode(2)
    washMachine.start()
}