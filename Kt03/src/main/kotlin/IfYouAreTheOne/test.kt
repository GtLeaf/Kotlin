package IfYouAreTheOne

fun main(args: Array<String>) {
//    getMaxAge2()
    groupBy()
}

//分组
fun groupBy(){
    println(GirlDatabase.groupBy {
        it.address
    })
}

//返回符合条件的第一个
fun findOption(){
    println(GirlDatabase.find {
        it.address == "广东"
    })
}

fun mapOption(){
    println(GirlDatabase.map {
        "${it.name} : ${it.age}"
    })
}

fun countOption(){
    println(GirlDatabase.count {
        it.age > 18
    })
}

//是否存在
fun anyOption(){
    println(GirlDatabase.any{
        it.age == 18
    })
}

fun filerData(){
    println(GirlDatabase.filter {
        (it.age>18) and (it.height>168)
    })
}

fun getMaxAge2(){
    println(GirlDatabase.maxBy { it.age })
}
fun getMinAge2(){
    println(GirlDatabase.minBy { it.age })
}

fun getMaxAge(){
    var maxAge:Int = 0
    for (girl in GirlDatabase){
        if (girl.age>maxAge){
            maxAge = girl.age
        }
    }
    println("$maxAge")
}