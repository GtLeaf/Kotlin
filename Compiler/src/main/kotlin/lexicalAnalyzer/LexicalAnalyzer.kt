package lexicalAnalyzer

import java.io.BufferedReader
import java.io.File

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
    var retainWordList = mutableListOf<TokenInfo>()

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
        IDENTIFIER
    }

    init {
        val file = File(fileName)
        val bytes: ByteArray = file.readBytes()
        val br: BufferedReader = file.bufferedReader()
        readFile(br)
    }

    fun readFile(br: BufferedReader){

        mCh = br.read()
        colNumber += 1
        while (mCh != -1){
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
                    dealAnnotate(mPre_ch, mCh, br)
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
                    if (delimiter.local == DoubleCharInfo.CharLocal.LEFT){
                        doubleDelimiterStack.add(delimiter)
                    }else if (delimiter.local == DoubleCharInfo.CharLocal.RIGHT) {
                        for (leftDelimiter in doubleDelimiterStack){
                            leftDelimiter.type == delimiter.type
                            doubleDelimiterStack.remove(delimiter)
                        }
                    }
                }
                //其他字符
                LexicalAnalyzer.Charactor.OTHER -> {}
            }

            //计算每个字符所在的位置
            if (9 == mCh){//制表符一般为4个空格
                colNumber += 9
            }else if(10 == mCh){    //回车键
                colNumber = 0
                rowNumber += 1
            }else{
                colNumber += 1
            }

            mCh = br.read()
            var str:Char = mCh.toChar()
            //保留前一个字符，用于判断注释
            mPre_ch = mCh
        }
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
    fun dealAnnotate(pre_ch: Int, ch: Int,  br: BufferedReader){
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
        }
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
    //判断token
    fun tokenJudge(token:String): LexicalAnalyzer.TokenType {
        if (isRetainWord(token)){
            return LexicalAnalyzer.TokenType.RETAIN_WORD
        }
        return LexicalAnalyzer.TokenType.IDENTIFIER
    }
    //识别单词
    fun distinguish(){
        when(tokenJudge(token.toString())){
            //保留字
            LexicalAnalyzer.TokenType.RETAIN_WORD ->{
                retainWordList.add(lexicalAnalyzer.TokenInfo(token.toString(), "关键字"))
                token.delete(0, token.length)
            }
            //标识符
            LexicalAnalyzer.TokenType.IDENTIFIER ->{
                if (token.isEmpty()){
                    return
                }
                identifier.add(token.toString())
                token.delete(0, token.length)
            }
        }

    }
}