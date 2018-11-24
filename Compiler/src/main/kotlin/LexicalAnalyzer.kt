import java.io.BufferedReader
import java.io.File

class LexicalAnalyzer {
    var ch_index = -1
    var ch = -1
    var token = StringBuffer()
    val retainWord:Array<String> = arrayOf("class", "public", "static", "void", "main", "String", "int",
            "if", "else", "println", "return", "for")
    val doubleRelop:Array<String> = arrayOf(">=", "<=", "==", "!=", "&", "&&", "||")
    val singleRelop:Array<Char> = arrayOf('>','<','=','+','-','*','/','%')
    val singleDelimiter:Array<Char> = arrayOf(':',';',',')
    val doubleDelimiter:Array<Char> = arrayOf('(',')','{','}','[',']', '"')

    enum class Charactor{
        BLANK,
        SINGLE_RELOP,
        DOUBLE_RELOP,
        SINGLE_DELIMITER,
        DOUBLE_DELIMITER,
        NUMBER_POINT,
        CHARACTER,
        UNDERLINE,
        FAIL
    }

    fun readFile(path:String){
        val file = File(path)
        val bytes: ByteArray = file.readBytes()
        val br: BufferedReader = file.bufferedReader()
        ch = br.read()
        while (ch != -1){
            //分词
            when(charJudge(ch)){
                //空格
                Charactor.BLANK -> {
                    distinguish()
                }
                //数字
                Charactor.NUMBER_POINT -> {
                    appendToken(ch)
                }
                //字母
                Charactor.CHARACTER -> {
                    appendToken(ch)
                }
                //下划线
                Charactor.UNDERLINE -> {
                    appendToken(ch)
                }
                //单元运算符
                Charactor.SINGLE_RELOP -> {
                    distinguish()
                }
                //二元运算符
                Charactor.DOUBLE_RELOP -> {
                    distinguish()
                }
                //单界定符
                Charactor.SINGLE_DELIMITER -> {
                    distinguish()
                }
                //双界定符
                Charactor.DOUBLE_DELIMITER -> {
                    distinguish()
                }
                //其他字符
                Charactor.FAIL -> {}
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
    fun isSinglePoint(ch:Int):Boolean{
        for (single in singleRelop){
            if (single.equals(ch)){
                return true
            }
        }
        return false
    }
    //判断是否是双界符
    fun isDoublePoint(ch:Int):Boolean{
        for (single in doubleDelimiter){
            if (single.equals(ch)){
                return true
            }
        }
        return false
    }
    //判断是否为数字
    fun isDigit(ch:Int):Boolean{
        if (ch >= 48 && ch <= 57){
            return true
        }
        return false
    }
    //判断是否是字母
    fun isCharactor(ch: Int):Boolean{
        if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) {
            return true;
        }
        return false;
    }
    fun isUnderline(ch: Int):Boolean{
        return ch == 95
    }
    fun charJudge(ch: Int):Charactor{
        if (isBlank(ch)){
            return Charactor.BLANK
        }
        if (isSinglePoint(ch)){
            return Charactor.SINGLE_RELOP
        }
        if (isDoublePoint(ch)){
            return Charactor.DOUBLE_RELOP
        }
        if (isDoublePoint(ch)){
            return Charactor.DOUBLE_DELIMITER
        }
        if (isDoublePoint(ch)){
            return Charactor.NUMBER_POINT
        }
        if(isCharactor(ch)){
            return  Charactor.CHARACTER
        }
        if(isUnderline(ch)){
            return  Charactor.UNDERLINE
        }
        return Charactor.FAIL
    }
    fun appendToken(ch: Int){
        token.append(ch)
    }
    //识别单词
    fun distinguish(){

    }
}