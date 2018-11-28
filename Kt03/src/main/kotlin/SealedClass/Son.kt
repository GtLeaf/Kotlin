package SealedClass
/*
* 印方类:子类类型有限的class, sealed class更在意类型，枚举更在意数据
* 小母驴，小公驴，小公马生下来的儿子
* */
sealed class Son {

    fun sayHello(){
        println("大家好！")
    }

    class litteDonkey():Son()
    class litteMule():Son()
}