import org.json.JSONObject
import sun.misc.BASE64Decoder
import sun.misc.BASE64Encoder
import java.io.File
import java.io.FileInputStream

fun main(args: Array<String>) {
    var emptyOptions = HashMap<String, String>()
    var options = HashMap<String, String>()
    options.put("user_info", "小丑女")
    options.put("quality_control", "NORMAL")
    options.put("liveness_control", "LOW")
//    var face_token = "8ded7eea11c13f5477776a8e83aeeb0c"   //马修麦康纳
//    var face_token2 = "29239447ef2322097f02936194f4399d"  //小丑女

//    println(FaceDetectUtil.detect("img/clown_girl.jpg", options).toString(2))
//    println(FaceDetectUtil.addUser("img/clown_girl.jpg", "test_group", "clown_gril", options).toString())
//    println(FaceDetectUtil.getGroupUsers("test_group", emptyOptions))
    println(FaceDetectUtil.search("img/clown_girl.jpg","test_group", emptyOptions))
//    println(FaceDetectUtil.updateUser("img/clown_girl.jpg", "test_group", "clown_gril", FaceDetectUtil.emptyOption))

    //检测文件是否存在
    /*var filename = "img/matthew.jpg"
    var file = File(filename)
    println(FileInputStream(file).channel.size())*/
}

