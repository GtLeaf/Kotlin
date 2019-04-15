package designPattern.BuilderPattern

/*
* 建造者模式：
* 优点：
* 1.建造过程可精细化控制
* 2.不必知道具体实现细节
* 3.符合开闭原则
* 缺点：
* 1.不适用于差异较大的产品
* 2.不适用于内部变化复杂的产品，否则需要很多Builder
* 参考：https://www.cnblogs.com/liaoweipeng/p/5790603.html
* 参考：https://www.cnblogs.com/snailclimb/p/builderpattern.html
* */

interface Build{

    //构建水源
    fun buildWater(water: String):Build

    //构建陆地
    fun buildLand(land: String):Build

    //构建生物
    fun buildBiologcial(biological: String):Build

    //构建世界
    fun buildWorld():World
}

//星球类
class World(val water: String, val land:String, val biological:String){
    init {
        println("世界生成，水资源:$water, 陆地:$land，物种:$biological")
    }
}

//具体Build，即ConcreteBuilder
class WorldBuild : Build{

    var water = ""
    var land = ""
    var biological = ""

    override fun buildWater(water: String):Build {
        this.water = water
        return this
    }

    override fun buildLand(land: String):Build {
        this.land = land
        return this
    }

    override fun buildBiologcial(biological: String):Build {
        this.biological = biological
        return this
    }

    override fun buildWorld(): World {
        return World(water, land, biological)
    }
}

fun main(args:Array<String>){
    val builder = WorldBuild()
    val world = builder.buildWater("丰富")
            .buildLand("稀少")
            .buildBiologcial("丰富")
            .buildWorld()
}
