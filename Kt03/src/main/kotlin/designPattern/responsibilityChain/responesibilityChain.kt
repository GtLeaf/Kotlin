package designPattern.responsibilityChain





class FiletrChain{

    private val filters = ArrayList<Filetr>()

    private var index = 0

    //添加过滤器
    fun addFilter(f:Filetr):FiletrChain{
        filters.add(f)
        return this
    }
    //用list中的过滤器进行过滤
    fun proceed(request: Request, response: Response, chain:FiletrChain): Response{
        //判断是否过滤完成
        if (index == filters.size){
            response.responseStr += request.requestStr
            return response
        }
        //每添加一个过滤规则，index自增
        val f = filters[index]
        index++

        return f.doFiletr(request, response, chain)
    }
}
//过滤器接口
interface Filetr{
    fun doFiletr(request: Request, response: Response, chain:FiletrChain): Response
}
//敏感词过滤器
class SenstiveFilter:Filetr{
    override fun doFiletr(request: Request, response: Response, chain: FiletrChain): Response {
        request.requestStr = "SenstiveFilter---" + request.requestStr
                .replace("敏感词","")
                .replace("暴恐", "") + "---SenstiveFilter"
        return chain.proceed(request, response, chain)
    }
}

//HTML过滤器
class HTMLFilter : Filetr{
    override fun doFiletr(request: Request, response: Response, chain: FiletrChain): Response {
        //将"<>"换成"[]"
        request.requestStr = "HTMLFilter---" + request.requestStr
                .replace("<","[")
                .replace(">", "]") + "---HTMLFilter"
        return chain.proceed(request, response, chain)
    }
}

fun main(args:Array<String>){
    val msg = "<Html>，敏感词，吃饭，暴恐，旅游"
    val request = Request.Builder()
            .requestStr(msg)
            .url("")
            .build()
    var respone = Response("response:")

    val filerChain = FiletrChain()
    respone = filerChain.addFilter(HTMLFilter())
            .addFilter(SenstiveFilter())
            .proceed(request, respone, filerChain)
    println(respone.responseStr)
}

class responesibilityChain {

}