fun main(arg:Array<String>){
    var arr = intArrayOf(51, 46, 32, 18, 65, 97, 82, 30, 77, 50,2,33,12,100)
//    var arr = intArrayOf(10, 8, 2)
//    bubbleSort(arr)
//    selectSort(arr)
    quickSort2(arr, 0, arr.size-1)
    arr.forEach { print("$it,") }
}
//冒泡排序，时间复杂度O(n²)
private fun bubbleSort(arr:IntArray){
    for(i in 1 until arr.size){//剩下最后一个数时不用比较，减少一趟
        for (j in 0 until arr.size-i){//用到下标j+1，防止越界，所以-1。每一趟最后一个数是已经排好序，所以-i
            if (arr[j] > arr[j+1]){
                var temp = arr[j]
                arr[j] = arr[j+1]
                arr[j+1] = temp
            }
            arr.forEach { print("$it,") }
            println()
        }
    }
}

//选择排序 时间复杂度0(n²)
fun selectSort(arr:IntArray){
    for (i in 0 until arr.size-1){
        for (j in i+1 until arr.size-1){
            if (arr[i] > arr[j]){
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }
    }
}

//快速排序
fun quickSort(arr:IntArray, start:Int, end:Int){
    if (start>=end){
        return
    }
    var left = start
    var right = end
    val key = arr[start]
    while (left<right){
        while (left<right && arr[right]>=key){
            right--
        }
        while (left<right && arr[left]<=key){
            left++
        }
        if (left<right){
            val temp = arr[left]
            arr[left] = arr[right]
            arr[right] = temp
        }
    }
    arr[start] = arr[left]
    arr[left] = key
    arr.forEach { print("$it,") }
    println()
    quickSort(arr, start, left-1)
    quickSort(arr, left+1, end)
}

fun quickSort2(arr:IntArray, start: Int, end: Int){
    var left = start
    var right = end
    var key = arr[left]

    while (left<right){
        while (left<right && arr[right]>=key){
            right--
        }
        if (left<right){
            arr[left] = arr[right]
            arr[right] = key
            left++
        }
        while (left<right && arr[right]<=key){
            left++
        }
        if (left<right){
            var temp =arr[right]
            arr[right] = arr[left]
            arr[left] = temp
            right--
        }
        //递归调用
        if (start<left){
            quickSort(arr, start, left-1)
        }
        if (end>right){
            quickSort(arr, right+1, end)
        }
    }
}