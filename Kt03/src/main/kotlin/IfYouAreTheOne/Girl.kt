package IfYouAreTheOne

import java.util.*

data class Girl (var name:String, var age:Int, var height:Int, var address:String)
var GirlDatabase = listOf<Girl>(
        Girl("依儿", 18, 168, "山东"),
        Girl("笑笑", 19, 175, "河南"),
        Girl("小百合", 17, 155, "福建"),
        Girl("michel", 22, 148, "广东"),
        Girl("玲儿", 23, 169, "广东")
)

fun filterGirlByAddress(address:String){
    var girls = ArrayList<Girl>()
    for (girl in GirlDatabase){
        if (girl.address == address){
            girls.add(girl)
            println("${girl.address} ${girl.age}岁的美女 ${girl.name}")
        }
    }
}

fun filterGirlByAge(age:Int){
    var girls = ArrayList<Girl>()
    for (girl in GirlDatabase){
        if (girl.age < age){
            girls.add(girl)
            println("${girl.address} ${girl.age}岁的美女 ${girl.name}")
        }
    }
}

fun filterGirlByAddressHeightAge(address: String, height:Int, age:Int){
    var girls = ArrayList<Girl>()
    for (girl in GirlDatabase){
        if ((girl.age<age) and (girl.height>height) and (girl.address==address)){
            girls.add(girl)
            println("${girl.address} ${girl.age}岁的美女 ${girl.name} 身高${girl.height}")
        }
    }
}

infix fun List<Girl>.findGirlAgeLessThan(age:Int){
    filter { it.age < age }.forEach(::println)
}

infix fun List<Girl>.findGirlComeFrom(address:String){
    filter { it.address == address }.forEach(::println)
}