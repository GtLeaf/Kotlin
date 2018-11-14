package Abstract

class Woman(name:String):Human(name) {
    override fun eat() {
        println("${name}嘤嘤嘤小口吃")
    }
}