import java.io.BufferedReader
import java.io.File

class LexicalAnalyzer {
    var ch_index = -1
    var ch = -1
    var token = StringBuffer()
    val retainWord:Array<String> = arrayOf("main", "fun", "int", "String",
            "if", "else", "return", "for")
    val singlePoint:Array<Char> = arrayOf('>','<','=','+','-','*','/','%')
    val doublePointint:Array<char> =

    fun readFile(path:String){
        val file = File(path)
        val bytes: ByteArray = file.readBytes()
        val br: BufferedReader = file.bufferedReader()
        ch = br.read()
        while (ch != -1){
            if (isBlank(ch)){
                //如果是空格，则视为单词输入结束，开始识别

            }else{

            }
            ch = br.read()
        }
    }

    //判断是否是空格
    fun isBlank(ch:Int):Boolean{
        if (32 == ch){
            return true
        }
        return false
    }

    //判断是否是单界符
    fun isSinglePoint(ch:Int){

    }
    //判断是否是双界符
    fun isDoublePoint(ch:Int){

    }
    //识别单词
    fun distinguish(){

    }
}