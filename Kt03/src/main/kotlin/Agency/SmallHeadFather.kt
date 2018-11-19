package Agency

/*
* 大头儿子
* */
class SmallHeadFather:IWashBowl by BigHeadSon{
    override fun washing() {
        println("我是小头爸爸，赚了10块钱")
        BigHeadSon.washing()
        println("我让儿子把碗洗好了")
    }
}