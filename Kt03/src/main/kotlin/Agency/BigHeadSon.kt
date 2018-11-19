package Agency

/*
* 大头儿子
* object 在内存中自动创建，并且有且只有一个
* */
object BigHeadSon:IWashBowl {
    override fun washing() {
        println("我是大头儿子，洗一次碗1块钱")
    }
}