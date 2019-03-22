package interviewer;

public class SortFunctionJava {
    public static void main(String[] args){
        /*int[] arr = new int[]{51, 46, 32, 18, 65, 97, 82, 30, 77, 50,2,33,12,100};
        MySort sort = new MySort();
        arr = sort.heapSort(arr);
        for (int num :arr){
            System.out.print(num+",");
        }*/
    }
}



//-----------------------堆排序算法，开始：10:39----------------------
class MySort{

    public int[] heapSort(int[] arr){
        if (null == arr || arr.length <= 1){
            return arr;
        }
        int length = arr.length;
        int temp;
        //循环建堆
        for(int i = length/2-1; i>=0 ; i--){
            max_heapAdjust(arr, i, length);
        }
        //堆调整
        for (int i=length-1; i>0; i--){
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            max_heapAdjust(arr, 0, i);
        }

        return arr;
    }

    public void max_heapAdjust(int[] arr, int index, int length){
        //如果没有左右节点，返回
        if (2*index > length){
            return;
        }

        int cur = index;
        int left = index*2+1;
        int right = index*2+2;
        //如果当前节点小于左结点，交换
        if(left < length && arr[left]>arr[cur]){
            cur = left;
        }
        //如果当前节点小于右结点，交换
        if (right<length && arr[right]>arr[cur]){
            cur = right;
        }
        if (cur != index){
            int temp = arr[index];
            arr[index] = arr[cur];
            arr[cur] = temp;
            max_heapAdjust(arr, cur, length);
        }
    }
    //-------------------------结束：11：15，耗时：36分钟----------------------------
}
