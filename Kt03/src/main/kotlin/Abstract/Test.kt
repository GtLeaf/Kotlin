package Abstract

fun main(args: Array<String>) {
    var man = Man("金三月半")
    var woman = Woman("铁娘子")
    man.eat()
    woman.eat()

    var humanList = listOf<Human>(man, woman)
    for (m in humanList){
        m.eat()
    }
}