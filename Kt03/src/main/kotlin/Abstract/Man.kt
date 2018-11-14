package Abstract

class Man(name:String):Human(name) {
    override fun eat() {
        println("${name}大口吃肉")
    }
}