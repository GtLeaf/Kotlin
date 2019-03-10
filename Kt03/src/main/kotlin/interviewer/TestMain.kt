package interviewer

class TestMain {


}

fun main(args:Array<String>){
    var outer = Outer()
    var inner = outer.Inner()
    var inner1 = Outer().Inner()

    println("$inner.hashCode() + $inner1.hashCode()")
}