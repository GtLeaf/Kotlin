fun main(arg:Array<String>){
    var arr = intArrayOf(51, 46, 32, 18, 65, 97, 82, 30, 77, 50,2,33,12,100)
//    var arr = intArrayOf(10, 8, 2)
//    bubbleSort(arr)
//    selectSort(arr)
//    quickSort2(arr, 0, arr.size-1)
//    insertSort(arr)
    mergeSort(arr, 0, arr.size-1)
    arr.forEach { print("$it,") }
}
//冒泡排序，时间复杂度O(n²)l
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
//快速排序2
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

//插入排序,对于内部部分有序情况有利,O(n²)~O(n)
fun insertSort(arr:IntArray){

    for(i in 0 until arr.size){
        for(j in i downTo  1){//可以使用while循环，一次插入到位
            if (arr[j]<arr[j-1]){
                val temp = arr[j]
                arr[j] = arr[j-1]
                arr[j-1] = temp
            }
        }
    }
}

//归并排序
fun mergeSort(arr:IntArray, start: Int, end: Int){
    if(start<end){
        var mid = (start + end) / 2
        mergeSort(arr, start, mid)
        mergeSort(arr, mid+1, end)
        merge(arr, start, mid, end)
    }
}
//进行合并
fun merge(arr: IntArray, start: Int, mid:Int, end: Int){
    val arrNum1 = mid-start+1   //arr[start]~arr[mid]
    val arrNum2 = end-mid       //arr[mid+1]~arr[end]
    val leftArr = IntArray(arrNum1)
    val rightArr = IntArray(arrNum2)
    var i = 0
    var j = 0
    var place = start
    //将元素添加进对应的数组
    for (k in 0 until arrNum1){
        leftArr[k] = arr[k+start]
    }
    for (k in 0 until arrNum2){
        rightArr[k] = arr[k+mid+1]
    }

    //对元素进行归并
    while(i<arrNum1 && j<arrNum2){
        if (leftArr[i]<rightArr[j]){
            arr[place++] = leftArr[i++]
        }else{
            arr[place++] = rightArr[j++]
        }
    }
    //处理剩下的元素
    while(i<arrNum1){
        arr[place++] = leftArr[i++]
    }
    while (j<arrNum2){
        arr[place++] = rightArr[j++]
    }
}
