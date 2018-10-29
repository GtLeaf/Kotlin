
fun main(args: Array<out String>){
    val user = User(0, null);
    println(user)

    HelloKotlin::class.constructors.map { println(it)}
}
class HelloKotlin{
    fun hello(){

    }
}
