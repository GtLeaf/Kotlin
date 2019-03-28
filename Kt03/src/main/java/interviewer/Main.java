package interviewer;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        solve();
    }

    static class Point{
        int x;
        int y;
        boolean isArrive;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void solve(){
        Scanner in = new Scanner(System.in);
        List<Point> pointList = new ArrayList<Point>();
        pointList.add(new Point(0, 0));
        while (in.hasNextInt()){
            pointList.add(new Point(in.nextInt(), in.nextInt()));
            System.out.println("添加");
        }
        System.out.println("开始执行");
        System.out.print((int) findShortDistance(pointList, new Point(0, 0), 0));

    }

    /*private static double findShortDistance(List<Point> pointList, int index, double distance){

        if (index >= pointList.size()){
            return distance += getDistance(pointList.get(index-1), new Point(0,0));
        }
        double minDistance = Double.MAX_VALUE;
        for (int i=index; i<pointList.size(); i++){
            //取最小值
            minDistance = Math.min(minDistance, findShortDistance(pointList, index+1, distance));
        }

        return distance += minDistance;
    }*/

    private static double findShortDistance(List<Point> pointList, Point point, double distance){

        double min = Double.MAX_VALUE;
        boolean isFinish = true;
        for (Point p : pointList) {
            if (!p.isArrive){
                isFinish = false;
                p.isArrive = true;
                min = Math.min(min, findShortDistance(pointList, p, distance));
                p.isArrive = false;
            }

        }
        if (isFinish && point.x != 0 && point.y != 0){
            return getDistance(point, new Point(0, 0));
        }

        return distance+min;
    }

    private static double getDistance(Point pointStart, Point pointEnd){
        return Math.sqrt(Math.pow((pointStart.x-pointEnd.x), 2) + Math.pow((pointStart.y-pointEnd.y), 2));
    }

    /*private static void solve(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String str = in.next();
        int len = str.length()/n;
        StringBuilder result = new StringBuilder();
        Stack<Character> stackCh = new Stack<Character>();
        List<String> stringList = new ArrayList<>();
        for (int i=0; i<n; i++){
            stringList.add(str.substring(i*len, i*len+len));
        }
        for (String s : stringList){
            char[] chars = s.toCharArray();
            //小端
            if ('1' == chars[0]){
                result.append(s.substring(1)).append(" ");
            }else {//大端
                for (int i=1; i<s.length(); i++){
                    stackCh.push(chars[i]);
                }
                while (!stackCh.isEmpty()){
                    result.append(stackCh.pop());
                }
                result.append(" ");
            }
        }

        System.out.print(result.toString().substring(0, result.length()-1));
    }*/
}
/*public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] numArr = new int[n];
        //边界处理
        if (0 == n) {
            return;
        } else if (1 == n) {
            System.out.println(numArr[0]);
        }
        int left = 0;
        int right = n - 1;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            if (num%2 == 1){
                numArr[left++] = num;
            }else {
                numArr[right--] = num;
            }
        }
        for (int num : numArr) {
            System.out.print(num + " ");
        }
    }

    //测试:{1},{0},{1,2,4},{1,3,5,7},{2,4,6,8},{1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,5,9,9,8,8,8}
    private static void solve(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            //奇数在左边
            while (left < right && arr[left] % 2 == 1) {
                left++;
            }
            //偶数在右边
            while (left < right && arr[right] % 2 == 0) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[left] ^ arr[right];
                arr[right] = arr[left] ^ arr[right];
                arr[left] = arr[left] ^ arr[right];
            }
        }

        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
    }*/
/*static HashMap<String, Object> resultMap = new HashMap<>();
    static int result = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[] charArr = new char[n];
        for (int i=0; i<n; i++){
            charArr[i] = in.next().toCharArray()[0];
        }
        //边界处理
        if (0 == n || 1 == n){
            System.out.println(n);
        }
        solve(charArr, 0);
        System.out.println(result);
    }


    private static void solve(char[] charArr, int index){
        if (index == charArr.length-1){
            result++;
            return;
        }

        for(int i=0; i<charArr.length-index; i++){
            swap(charArr, index, index+i);
            solve(charArr, index+1);
            swap(charArr, index, index+i);
        }
    }

    private static void swap(char[] charArr,int left, int right){
        char temp = charArr[left];
        charArr[left] = charArr[right];
        charArr[right] = temp;
    }*/

/*Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> student = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for (int num=0; num<n; num++){
            student.add(in.nextInt());
        }
        //处理特殊情况
        if (1 == n){
            System.out.println(student.get(0));
            return;
        }

        for (int i=student.size()-1; i>=0; i--){
            int front = result.size();
            //最右边不用看齐
            if (i == student.size()-1){
                result.add(0);
                continue;
            }
            //向右看齐，右边高于左边
            if(student.get(i+1)<=student.get(i)){
                while(--front>=0){
                    if (result.get(front)==0){
                        if (!(student.get(result.get(front)) <= student.get(i))){
                            break;
                        }

                    }else {
                        if (!(student.get(result.get(front)-1) <= student.get(i))){
                            break;
                        }
                    }
                }
            }else {
                result.add(i+2);
                continue;
            }

            if (-1 == front){
                //没有可以看齐的
                result.add(0);
            }else {
                result.add(result.get(front));
            }
        }
        for (int index=result.size()-1; index>=0; index--){
            System.out.println(result.get(index));
        }*/
/*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int m = sc.nextInt();
        if (0 == n){
            return;
        }
        if (1 == n){
            System.out.println(1);
            return;
        }

        List people = new ArrayList<Integer>();

        for (int i=0; i<n; i++){
            people.add(i+1);
        }
        for (int j = n; j>0; j--){
            while (s>j){
                s = s-j;
            }
            s = s+m-1;
            while (s>j){
                s = s-j;
            }
            System.out.println(people.remove(s-1));
        }
    }*/