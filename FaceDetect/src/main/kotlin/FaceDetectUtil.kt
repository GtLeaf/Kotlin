import com.baidu.aip.face.AipFace
import org.json.JSONObject
import sun.misc.BASE64Encoder
import java.io.FileInputStream

object FaceDetectUtil {
    val APP_ID = "14871711"
    val API_KEY  = "QsMSFvk4phCqlxdvlDCTYBvi"
    val SECRET_KEY  = "m9fqMZiLqeRnI7KZTVvucky9azasfERE"
    val client = AipFace(APP_ID, API_KEY, SECRET_KEY)
    val emptyOption = HashMap<String, String>()

    val IMAGE_TYPE_BASE64 = "BASE64"

    init {
        client.setConnectionTimeoutInMillis(2000)
        client.setSocketTimeoutInMillis(60000)
    }

    //将图片转为base64编码
    fun toBase64(path:String):String{
        var fileIn = FileInputStream(path)
        val fileData = ByteArray(fileIn.available())
        fileIn.read(fileData)
        return BASE64Encoder().encode(fileData)
    }
    //人脸检测
    fun detect( path:String, options:HashMap<String, String>):JSONObject{
        return client.detect(toBase64(path), IMAGE_TYPE_BASE64, options)
    }
    //人脸注册
    fun addUser(path:String, groupId:String, userId:String, options: HashMap<String, String>):JSONObject{
        /*var mOptions = HashMap<String, String>()
        mOptions.put("quality_control", "NORMAL")
        mOptions.put("liveness_control", "LOW")
        if (0 == options.size){
            options.putAll(mOptions)
        }*/
        return client.addUser(toBase64(path), IMAGE_TYPE_BASE64, groupId, userId, options)
    }

    //人脸更新
    fun updateUser(path: String, groupId: String, userId: String, options: HashMap<String, String>):JSONObject{
        return client.updateUser(toBase64(path), IMAGE_TYPE_BASE64, groupId, userId, options);
    }

    //人脸搜索
    fun search(path:String, groupIdList:String, options: HashMap<String, String>):JSONObject{
        return client.search(toBase64(path), IMAGE_TYPE_BASE64, groupIdList, options)
    }
    //创建用户组
    fun groupAdd(groupId:String, options: HashMap<String, String>):JSONObject{
        return client.groupAdd(groupId, options)
    }
    //删除用户组
    fun groupDelete(groupId:String, options: HashMap<String, String>):JSONObject{
        return client.groupDelete(groupId, options)
    }
    //组列表查询
    fun getGroupList(options: HashMap<String, String>):JSONObject{
        return client.getGroupList(options)
    }
    //获取用户人脸列表
    fun faceGetlist(userId:String, groupId:String, options: HashMap<String, String>):JSONObject{
        return client.faceGetlist(userId, groupId, options)
    }
    //获取用户列表
    fun getGroupUsers(groupId:String, options: HashMap<String, String>):JSONObject{
        return client.getGroupUsers(groupId, options);
    }
    //用户信息查询
    fun getUser(userId: String, groupId: String, options: HashMap<String, String>):JSONObject{
       return client.getUser(userId, groupId, options)
    }
}