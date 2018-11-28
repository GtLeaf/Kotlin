package SealedClass

fun main(args: Array<String>) {
    var s:Son = Son.litteDonkey()
    var s2:Son = Son.litteMule()
//    s.sayHello()
//    s2.sayHello()

    var list = listOf<Son>(s, s2)
    for (v in list){
        if (v is Son.litteDonkey){
            v.sayHello()
        }
    }
}