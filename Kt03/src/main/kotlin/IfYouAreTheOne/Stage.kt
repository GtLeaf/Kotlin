package IfYouAreTheOne

fun main(args: Array<String>) {
    var henanGirl = ArrayList<Girl>()
//    filterGirlByAddress("广东")
//    filterGirlByAge(30)
//    filterGirlByAddressHeightAge("广东", 160, 25)

    //高阶函数
    /*GirlDatabase.groupBy {
        it.address
    }["广东"]?.forEach { println(it) }*/

    //DSL
//    GirlDatabase.findGirlAgeLessThan(25)
    //中缀表达式
//    GirlDatabase findGirlAgeLessThan 25
    GirlDatabase findGirlComeFrom "广东"
}