package lexicalAnalyzer

import com.sun.org.apache.xpath.internal.operations.Bool
import jdk.nashorn.internal.parser.Token
import java.io.BufferedReader
import java.io.File
import kotlin.system.exitProcess

class LexicalAnalyzer(fileName:String) {
    var ch_index = -1
    var mPre_ch = -1
    var mCh = -1
    var rowNumber = 0
    var colNumber = 0
    var token = StringBuffer()
    var identifier = mutableListOf<String>()
    val retainWord:Array<String> = arrayOf("class", "public", "static", "void", "main", "String", "int",
            "if", "else", "println", "return", "for")
    val singleRelop:Array<Char> = arrayOf('>','<','=','+','-','*','/','%')
    val doubleRelop:Array<String> = arrayOf(">=", "<=", "==", "!=", "&", "&&", "||")
    val singleDelimiter:Array<Char> = arrayOf(':',';',',')
    val doubleDelimiter:Array<Char> = arrayOf('(',')','{','}','[',']', '"')//成对出现
    //判断成对的符号栈
    var doubleDelimiterStack = mutableListOf<DoubleCharInfo>()
    //用于输出
    //保留字列表
    var retainWordList = mutableListOf<TokenInfo>()
    //常整数
    var constantIntegerList = mutableListOf<TokenInfo>()
    //单元运算符
    var singleRelopList = mutableListOf<TokenInfo>()

    enum class Charactor{
        BLANK,
        SINGLE_RELOP,
        DOUBLE_RELOP,
        SINGLE_DELIMITER,
        DOUBLE_DELIMITER,
        NUMBER_POINT,
        CHARACTER,
        UNDERLINE,
        OTHER
    }
    enum class TokenType{
        RETAIN_WORD,
        CONSTANT_INTEGER,
        IDENTIFIER
    }

    init {
        val file = File(fileName)
        val br: BufferedReader = file.bufferedReader()
        readFile(br)
    }
    //处理字符串
    fun dealString(ch: Int, br: BufferedReader):Boolean{
        if (34 != ch){
            return false
        }
        while (true){
            if (br.read() == 34){
                break
            }
        }
        return true
    }

    fun readFile(br: BufferedReader){

        mCh = br.read()
        colNumber += 1
        while (mCh != -1){

            //计算每个字符所在的位置
            if (9 == mCh){//制表符一般为4个空格
                colNumber += 9
            }else if(10 == mCh){    //回车键
                colNumber = 0
                rowNumber += 1
            }else{
                colNumber += 1
            }


            //分词
            when(charJudge(mCh)){
                //空格
                LexicalAnalyzer.Charactor.BLANK -> {
                    distinguish()
                }
                //数字
                LexicalAnalyzer.Charactor.NUMBER_POINT -> {
                    appendToken(mCh)
                }
                //字母
                LexicalAnalyzer.Charactor.CHARACTER -> {
                    appendToken(mCh)
                }
                //下划线
                LexicalAnalyzer.Charactor.UNDERLINE -> {
                    appendToken(mCh)
                }
                //单元运算符
                LexicalAnalyzer.Charactor.SINGLE_RELOP -> {
                    //识别之前读到的字符串
                    distinguish()
                    //如果ch是'/'，则可能是注释，处理注释
                    if (!dealAnnotate(mPre_ch, mCh, br)){
                        singleRelopList.add(TokenInfo(mCh.toChar().toString(), "单元运算符"))
                    }
                }
                //二元运算符
                LexicalAnalyzer.Charactor.DOUBLE_RELOP -> {
                    distinguish()
                }
                //单界定符
                LexicalAnalyzer.Charactor.SINGLE_DELIMITER -> {
                    distinguish()
                }
                //双界定符
                LexicalAnalyzer.Charactor.DOUBLE_DELIMITER -> {
                    distinguish()
                    var delimiter = DoubleCharInfo(mCh, rowNumber, colNumber)
                    //如果是左括号则入栈，如果是右括号要做若干处理
                    if (delimiter.local == DoubleCharInfo.CharLocal.LEFT){
                        doubleDelimiterStack.add(delimiter)
                    }else if (delimiter.local == DoubleCharInfo.CharLocal.RIGHT) {
                        //如果右括号和最近的一个左括号不匹配，则报错
                        var lastDelimiterStack = doubleDelimiterStack.get(doubleDelimiterStack.size-1)
                        if (lastDelimiterStack.type != delimiter.type){
                            errorExit("缺少${lastDelimiterStack.tokenValue}(${delimiter.rowNumber}, ${delimiter.colNumber})")
                        }
                        //将末尾左括号弹栈
                        doubleDelimiterStack.removeAt(doubleDelimiterStack.size-1)
                    }
                    if(dealString(mCh, br)){
                        //将左引号弹栈
                        doubleDelimiterStack.removeAt(doubleDelimiterStack.size-1)
                    }
                }
                //其他字符
                LexicalAnalyzer.Charactor.OTHER -> {}
            }


            mCh = br.read()
            //保留前一个字符，用于判断注释
            mPre_ch = mCh
        }
        println("编译通过")
    }

    //出错退出
    fun errorExit(msg:String){
        println(msg)
        exitProcess(1)
    }

    //判断是否是空格
    fun isBlank(ch:Int):Boolean{
        if (32 == ch){
            return true
        }
        return false
    }

    //判断是否是运算
    fun isSingleRelop(ch:Int):Boolean{
        for (single in singleRelop){
            if (single.equals(ch.toChar())){
                return true
            }
        }
        return false
    }
    //判断是否是双运算
    fun isDoubleRelop(ch:Int):Boolean{
        for (single in doubleRelop){
            if (single.equals(ch.toChar())){
                return true
            }
        }
        return false
    }
    //判断是否是单界符
    fun isSingleDelimiter(ch:Int):Boolean{
        for (single in singleDelimiter){
            if (single.equals(ch.toChar())){
                return true
            }
        }
        return false
    }
    //判断是否是双界符
    fun isDoubleDelimiter(ch:Int):Boolean{
        for (single in doubleDelimiter){
            if (single.equals(ch.toChar())){
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
    //是否是下划线
    fun isUnderline(ch: Int):Boolean{
        return ch == 95
    }
    //是否是注释
    fun dealAnnotate(pre_ch: Int, ch: Int,  br: BufferedReader):Boolean{
        //如果这两个字符是'/*'，则判断为注释的开始
        if(47 == pre_ch && 42 == ch){
            while (true){
                var chAnnotate = br.read()
                if (-1 == chAnnotate){
                    println("程序出错")
                }
                //如果读到'*/'则结束注释
                if (42 == chAnnotate && 47 == chAnnotate){
                    break
                }
            }
            return true
        }
        //如果是'//'，则为单行注释
        if (47 == pre_ch && 47 == ch){
            while (true){
                var chAnnotate = br.read()
                if (-1 == chAnnotate){
                    println("程序出错")
                }
                //如果读到换行符'\n'，则单行注释结束
                if (13 == chAnnotate ){
                    break
                }
            }
            return  true
        }
        return false
    }
    //判断每个字符
    fun charJudge(ch: Int): LexicalAnalyzer.Charactor {
        //空格
        if (isBlank(ch)){
            return LexicalAnalyzer.Charactor.BLANK
        }
        //单运算符
        if (isSingleRelop(ch)){
            return LexicalAnalyzer.Charactor.SINGLE_RELOP
        }
        //双运算符
        if (isDoubleRelop(ch)){
            return LexicalAnalyzer.Charactor.DOUBLE_RELOP
        }
        //单界定符
        if (isSingleDelimiter(ch)){
            return LexicalAnalyzer.Charactor.SINGLE_DELIMITER
        }
        //双界定符
        if (isDoubleDelimiter(ch)){
            return LexicalAnalyzer.Charactor.DOUBLE_DELIMITER
        }
        //数字
        if (isDigit(ch)){
            return LexicalAnalyzer.Charactor.NUMBER_POINT
        }
        //字母
        if(isCharactor(ch)){
            return LexicalAnalyzer.Charactor.CHARACTER
        }
        //下划线
        if(isUnderline(ch)){
            return LexicalAnalyzer.Charactor.UNDERLINE
        }
        //其他字符
        return LexicalAnalyzer.Charactor.OTHER
    }
    fun appendToken(ch: Int){
        token.append(ch.toChar())
    }
    //是否是关键字
    fun isRetainWord(token:String):Boolean{
        for (word in retainWord){
            if (word.equals(token))
                return true
        }
        return false
    }
    //是否数字常量
    fun isConstantInteger(token:String):Boolean{
        //首字母不是数字，就肯定不是数字
        if(!isDigit(token[0].toInt())){
            return false
        }
        var isFirst = true
        for (word in token){
            //以数字开头，但包含字母，既不是标识符，也不是数字，报错
            if (isFirst){
                isFirst = false
            }
            if (!isDigit(word.toInt()))
                errorExit("标识符不符合命名规则(${rowNumber}, ${colNumber})")
        }
        return true
    }
    //判断token
    fun tokenJudge(token:String): LexicalAnalyzer.TokenType {
        if (isRetainWord(token)){
            return LexicalAnalyzer.TokenType.RETAIN_WORD
        }
        if (isConstantInteger(token)){
            return LexicalAnalyzer.TokenType.CONSTANT_INTEGER
        }
        return LexicalAnalyzer.TokenType.IDENTIFIER
    }
    //识别单词
    fun distinguish(){
        if (token.isEmpty())
            return
        when(tokenJudge(token.toString())){
            //保留字
            LexicalAnalyzer.TokenType.RETAIN_WORD ->{
                retainWordList.add(lexicalAnalyzer.TokenInfo(token.toString(), "关键字"))
                token.delete(0, token.length)
            }
            //标识符
            LexicalAnalyzer.TokenType.IDENTIFIER ->{
                identifier.add(token.toString())
                token.delete(0, token.length)
            }
        //常整数
            LexicalAnalyzer.TokenType.CONSTANT_INTEGER -> {
                constantIntegerList.add(lexicalAnalyzer.TokenInfo(token.toString(), "常整数"))
                token.delete(0, token.length)
            }
        }

    }
}