package interviewer;

public class FindFunction {
}

//二分查找
class KindsOfFindFunction{

    public int binarySearch(int[] data, int key){
        int mid = -1;
        int left = 0;
        int right = data.length-1;

        //data[mid]与key值，如果key较小，在左边查找，反之在右边查找
        while (left<=right){
            mid = (left+right)/2;
            if (key == data[mid]){
                return mid;
            }else if (data[mid] > key){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return -1;
    }
}