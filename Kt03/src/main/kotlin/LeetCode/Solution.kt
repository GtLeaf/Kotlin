package LeetCode

import kotlin.math.max

fun main(args:Array<String>){
//    print(convert("LEETCODEISHIRING", 3))
//    print(reverse(2147483647))
//    print(isPalindrome2(121))
//    print("aaab".matches(Regex("a*b")))
    println(maxArea(intArrayOf(1,2,3,4)))
}

fun convert(s:String, numRows:Int):String{
    if (null == s || s.length <= numRows || 1 == numRows) return s
    var str = ""
    val numColumn = Math.ceil(s.length/(2*numRows.toDouble()-2)*(numRows-1)).toInt()

    val cellCharNum = 2*numRows-2   //每个单元的字符数
    val cellColumnNum = numRows-1   //每个单元列数
    var chars = Array(numColumn){CharArray(numRows)}
    var position = 0
    for (myChar in s){

        var remainder = position%cellCharNum    //余下不足一个单元的数量

        var x: Int //下标从0开始，要-1
        var y: Int
        if (remainder>(numRows-1)){
            x = position/cellCharNum*cellColumnNum+remainder-numRows+1  //位置/单元字符*单元所占列+剩余数位-行数+1
            y = 2*(numRows-1)-remainder
        }else{
            x = (position/cellCharNum)*cellColumnNum
            y = remainder //下标从0开始，要-1
        }
        println("x:${x},y:${y}")
        chars[x][y] = myChar
        position++
    }

    /*for (i in 0 until numRows){
        for (j in 0 until numColumn,){
            if (chars[j][i] != '\u0000'){
                str += chars[j][i]
            }
        }
    }*/
    chars.forEach {
        str += it.contentToString()
        println(str)
    }
    return str
}

fun reverse(x:Int):Int{
    var ret = 0
    var num = x
    while (num != 0){
        var pop = num%10
        num /= 10

        //ret会溢出，不能这么判断
//        if (ret*10+pop>Int.MAX_VALUE || ret*10+pop<Int.MIN_VALUE)
        if(ret>Int.MAX_VALUE/10 || (ret == Int.MAX_VALUE && pop>7)){
            return 0
        }
        if (ret<Int.MIN_VALUE/10 || (ret == Int.MIN_VALUE && pop<-8)){
           return 0
        }
        ret = ret*10 + pop
    }
    return ret
}

fun isPalindrome(x: Int): Boolean {
    if (x<0 || (x%10==0 && x!=0)) return false
    if (x < 10) return true
    var isOdd = true   //长度是否为奇数
    val mid = x.toString().length/2
    if (x.toString().length%2 == 0){
        isOdd = false
    }

    var ret = 0
    var num = x
    for (i in 0 until  mid){
        var pop = num%10
        num /= 10
        ret = ret*10+pop
    }

    if (isOdd){
        val odd = x/Math.pow(10.0, mid+1.0)
        if (ret == odd.toInt()){
            return true
        }
    }else{
        val even = x/Math.pow(10.0, mid.toDouble())
        if (ret == even.toInt()){
            return true
        }
    }
    return false
}

fun isPalindrome2(x:Int):Boolean{

    if (x<0 || (x%10==0 && x!=0)) return false
    if (x<10) return true

    var revertedNumber = 0
    var num = x
    while (num>revertedNumber){
        revertedNumber = revertedNumber*10 + num%10
        num /= 10
    }

    return num == revertedNumber || num == revertedNumber/10
}
/*
* 測試用例："", "a", "aa", "aab", "aabb", ".", "*", ".*"
* 不判断正则语法错误
* */
fun isMatch(s: String, p: String): Boolean {

    if (""==s && ""==p) return true
    if ("*"==p) return false
    if (""==s && p.get(2)!='*') return false

    var pChar = 0
    var nextPChar:Int
    var curChar = ""
    var curRex = ""
    var step = 1

    for (char in s){
        nextPChar = pChar+1
        //划分正则表达式
        if ('*' == p[nextPChar]){
            curRex = p.substring(pChar, nextPChar+1)
            step = 2
        }else{
            curRex = p[pChar].toString()
            step = 1
        }
        //判断是否符合
        if ("." == curRex){
            pChar += step
            continue
        }
        //字母
        if (curRex != char.toString()){
            return false
        }

    }

    return true
}
fun maxArea(height: IntArray): Int {
    var i = 0
    var j = height.size-1
    var maxArea = Math.min(height[i], height[j])*(j-i)

    while (i<j){
        //移动短的边
        if (height[i] <= height[j]){
            i++
        }else{
            j--
        }
        maxArea = Math.max(maxArea, Math.min(height[i], height[j])*(j-i))
    }
    return maxArea
}