fun main(args: Array<String>) {
    var nums = 1 .. 100     //[1, 100]开区间
    var nums2 = 1 until 100 //[1, 100)左闭右开
    var nums3 = 1 downTo -100
    var nums4 = nums.reversed() //倒序

    /*var result = 0
    for (num in nums){
        result += num
    }
    println("结果为：${result}")*/
    /*for (num in nums4 step 3){   //步长为2
        println(num)
    }*/
    println("长度为："+nums4.count()) //count为长度
}