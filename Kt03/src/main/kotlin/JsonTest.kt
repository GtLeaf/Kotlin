import net.sf.json.JSONObject

fun main(args:Array<String>){
    var json = "{'code':202, 'msg':'参数异常', 'data':null}"
    var json2 = "{'code':202, 'msg':'参数异常', 'data':{}}"
    var obj = JSONObject.fromObject(json)
    var data = obj.getString("data")
    var msg = obj.getString("msg")
    println("data:${data}, msg:${msg}")
}