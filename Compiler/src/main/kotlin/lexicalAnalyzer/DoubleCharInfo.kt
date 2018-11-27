package lexicalAnalyzer

class DoubleCharInfo(ch:Int, rowNumber:Int, colNumber:Int){
    var tokenValue = ""
    var type = DoubleCharType.UNKNOW
    var local = CharLocal.UNKNOW
    var rowNumber = 0
    var colNumber = 0

    companion object {
        var isLeft = true
    }

    enum class DoubleCharType{
        BRACES,     //大括号
        BRACKET,    //中括号
        PARENTHESES,//小括号
        DOUBLE_QUOTATION_MARKS,     //双引号
        SINGLE_QUOTATION_MARK,       //单引号
        UNKNOW      //未确定
    }
    enum class CharLocal{
        LEFT,
        RIGHT,
        UNKNOW
    }

    init{
        when(ch){
            //左大括号
            123 -> {
                this.type = DoubleCharType.BRACES
                this.local = CharLocal.LEFT
            }
            //右大括号
            125 -> {
                this.type = DoubleCharType.BRACES
                this.local = CharLocal.RIGHT
            }
            //左中括号
            91 -> {
                this.type = DoubleCharType.BRACKET
                this.local = CharLocal.LEFT
            }
            //右中括号
            93 -> {
                this.type = DoubleCharType.BRACKET
                this.local = CharLocal.RIGHT

            }
            //左小括号
            40 -> {
                this.type = DoubleCharType.PARENTHESES
                this.local = CharLocal.LEFT
            }
            //右小括号
            41 -> {
                this.type = DoubleCharType.PARENTHESES
                this.local = CharLocal.RIGHT
            }
            //双引号
            34 -> {
                this.type = DoubleCharType.DOUBLE_QUOTATION_MARKS
                this.local = CharLocal.LEFT
            }
        }
        this.rowNumber = rowNumber
        this.colNumber = colNumber
        this.tokenValue = ch.toChar().toString()
    }
}