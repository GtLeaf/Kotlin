package designPattern.responsibilityChain

import java.io.IOException

interface Interceptor{
    fun intercept(chain:Chain): Response

    interface Chain{
        fun request(): Request

        fun proceed(request: Request): Response
    }
}



//响应类
class Response(var responseStr:String){}

//请求类
class Request(builder: Builder) {
    var requestStr:String
    private var url:String? = null
    private var method:String? = null

    init {
        this.requestStr = builder.requestStr
        this.url = builder.url
        this.method = builder.method
    }

    //静态内部类
    class Builder{
        var requestStr = ""
        var url:String? = null
        var method:String? = null

        init {
            this.method = "GET"
        }

        fun url(url:String):Builder{
            this.url = url
            return this
        }
        fun requestStr(requestStr:String):Builder{
            this.requestStr = requestStr
            return this
        }

        fun build():Request{
            if (url == null) throw IllegalStateException("url == null")
            return Request(this)
        }
    }
}

//向服务器发送请求的拦截器
class CallServerInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        //模拟服务器返回数据
        return Response("<Html>，敏感词，吃饭，暴恐，旅游")
    }

}

class HTMLInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        val oldRequest = chain.request()
        //do something

        val newRequest = oldRequest

        val response = chain.proceed(newRequest)
        response.responseStr = response.responseStr
                .replace("<", "[")
                .replace(">", "]")
        return response
    }
}
class SenstiveInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val response = chain.proceed(chain.request())

        response.responseStr = response.responseStr
                .replace("敏感词","")
                .replace("暴恐", "")
        return response
    }
}

class InterceptorChain(val interceptors:List<Interceptor>, var index:Int, val request: Request):Interceptor.Chain{
    override fun request(): Request {
        return request
    }

    override fun proceed(request: Request): Response {
        //检查是否有对应的拦截器
        if (index >= interceptors.size){
            throw  AssertionError()
        }
        //获取当前链位置对应的拦截器
        val interceptor = interceptors[index]
        //获取下一个链
        val next = InterceptorChain(interceptors, index+1, request)
        //当前拦截器进行拦截处理
        val response = interceptor.intercept(next)
        //do check for response

        return response
    }

}

class Call(val originalRequest: Request){
    @Throws(IOException::class)
    fun getResponseWithInterceptorChain(): Response {
        val interceptors = ArrayList<Interceptor>()
        //添加拦截器,可以暴露方法，以添加自定义拦截器
        interceptors.add(HTMLInterceptor())
        interceptors.add(SenstiveInterceptor())
        interceptors.add(CallServerInterceptor())
        val chain = InterceptorChain(interceptors, 0, originalRequest)
        return chain.proceed(originalRequest)
    }
}

fun main(args:Array<String>){

    val request = Request.Builder()
            .url("url")
            .build()
    val call = Call(request)
    val response = call.getResponseWithInterceptorChain()
    println(response.responseStr)
}