import java.text.DecimalFormat;
import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] stayTimes = new int[n];
        for (int i=0; i<n; i++){
            stayTimes[i] = Integer.parseInt(in.nextLine());
        }
    }
    static class MyType{
        int index;
        double time;

        public MyType(int index, double time) {
            this.index = index;
            this.time = time;
        }
    }

    public static void solveIncreaseSub(int[] arr){
        double[] dp = new double[arr.length];
        dp[0] = 1;
        double maxLen = codeIncreaseSub(arr, arr.length-1, dp, 0);
        int maxLenIndex = 0;
        for(int i=0; i<arr.length; i++){
            if (maxLen == dp[i]){
                maxLenIndex = i;
            }
        }
        ArrayList<Double> htmlList = new ArrayList<>();
        ArrayList<MyType> htmlList2 = new ArrayList<>();
        for(int i=maxLenIndex; i>=0; i--){
            htmlList2.add(new MyType(i, dp[i]));
        }
        //用户流失
        int useLossNum = 0;
        //去除停留时间比maxLenIndex长的
        for (int i=maxLenIndex; i>=0; i--){
            while (true){
                if (i>0 && dp[i-1] > dp[i]){
                    htmlList2.remove(i-1);
                    htmlList2.set(i, new MyType(i-1, htmlList2.get(i).time*1.2));
                    useLossNum++;
                    i--;
                }else {
                    break;
                }
            }
        }

        double maxTime = 0.0;
        for (MyType num : htmlList2){
            maxTime += num.time;
        }

        System.out.println(maxTime+"");
        for (MyType num : htmlList2){
            System.out.print(dp[num.index]+" ");
        }
        for (MyType num : htmlList2){
            System.out.print(num.time+" ");
        }
    }

    //找以index为结尾的最大递增子序列
    public static double codeIncreaseSub(int[] arr, int index, double[] dp, double max){
        //basecase
        if (index < 0){
            return 0;
        }
        //等于不算递增
        int tempIndex = index-1;
        //找第一个小于等于arr[index]的
        while(tempIndex >= 0 && arr[tempIndex] >= arr[index]){
            tempIndex--;
        }
        //找不到
        if (tempIndex < 0){
            return dp[index] = 1.0;
        }
        //找到了
        dp[index] = (dp[tempIndex] != 0 ? dp[tempIndex] : codeIncreaseSub(arr, tempIndex, dp, max)) + 1;
        //取最大值
        max = Math.max(dp[index], dp[index-1] != 0 ? dp[index-1] : codeIncreaseSub(arr, index-1, dp, max));
        return max;
    }

}

//------------微众银行---------------
/*public static void main(String[] args){
        System.out.println(solveBinryOneNumber());
    }

    public static int solveBinryOneNumber(){
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int w = b-c;
        if (a > w){
            return w+1;
        }
        return a;
    }*/

//-----------华为分割字符串---------------------------
/*public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int strNum = in.nextInt();
        //边界处理
        if (0 == strNum){
            return;
        }

        String[] strings = new String[strNum];
        for (int i=0; i<strNum; i++){
            strings[i] = in.next();
        }
        //比较器
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char[] chars1 = o1.toCharArray();
                char[] chars2 = o2.toCharArray();
                int index = 0;
                int result = 0;
                while (index < chars1.length){
                    result = chars1[index] - chars2[index];
                    index++;
                    if ( result != 0 ){
                        return result;
                    }
                }
                return 0;
            }
        });

        //将字符串补齐8的倍数，分割放入queue中

        //将字符串从queue中打印
    }*/


/*public static void main(String[] args){
//        System.out.println(solveChocolates());
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n < 6){
            System.out.println(0);
            return;
        }
        System.out.println((int)Math.pow(2, n-6)%666666666);
    }*/
/*
public static String solveWater(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int w = in.nextInt();
        int min = Integer.MAX_VALUE;
        int[] capacities = new int[w];
        for (int i = 0; i<n; i++){
            int temp = in.nextInt();
            min = Math.min(min, temp);
            capacities[i] = temp;
        }
        double maxWater = min*n*3/2.000000;
        DecimalFormat df = new DecimalFormat("0.000000");
        return  df.format(Math.min(maxWater, w));
    }*/
/*public static int solveB(){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();
        int mid = (int)Math.pow(2, n-1);
        if (k>mid){
            return 0;
        }

        int base = mid-k;
        int count = ((1+base)*base)/2;

        return (count+1)*2;
    }*/
//腾讯打怪，通过率90%
/*static int[] attacks;
    static int[] spends;
    static int maxAttack = 0;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        attacks = new int[num];
        spends = new int[num];
        for (int i=0; i<num; i++){
            int attack = in.nextInt();
            attacks[i] = attack;
            maxAttack = attack > maxAttack ? attack : maxAttack;
        }
        for (int i=0; i<num; i++){
            spends[i] = in.nextInt();
        }

        System.out.println(go(num, 0, 0));
    }

    public static int go(int num, int index, int curAttack){
        //通过峡谷
        if (index >= num){
            return 0;
        }

        if(curAttack >= maxAttack){
            return 0;
        }

        if (curAttack < attacks[index]){
            //战力小，必须雇佣
            return go(num, index+1, curAttack+attacks[index])+ spends[index];
        }else {
            //雇佣和不雇佣
            return Math.min(go(num, index+1, curAttack),
                    go(num, index+1, curAttack+attacks[index])+ spends[index]);
        }
    }*/

//腾讯喝果汁
/*public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int s = in.nextInt();
        int count = 0;
        List<Integer> fruitJuices = new ArrayList<>();
        for(int i=0; i<n; i++){
            int num = in.nextInt();
            count += num;
            fruitJuices.add(num);
        }
        if (count < s){
            System.out.println(-1);
            return;
        }
        if (count == s){
            System.out.println(0);
            return;
        }
        fruitJuices.sort(Comparator.comparingInt(o -> o));
        int average = (count-s)/n;
        if (fruitJuices.get(0) < average){
            System.out.println(fruitJuices.get(0));
        }else {
            System.out.println(average);
        }
    }*/


    /*public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        if (1 == len){
            System.out.println(in.nextInt());
            return;
        }
        int[] preOrder = new int[len];
        int[] inOrder = new int[len];
        for (int i=0; i<len; i++){
            preOrder[i] = in.nextInt();
        }
        for (int i=0; i<len; i++){
            inOrder[i] = in.nextInt();
        }
        postOrderTraverse1(construct(preOrder, inOrder, 0, preOrder.length-1, 0, inOrder.length-1));
//        postOrderTraverse1(construct2(preOrder, inOrder));
    }

    public static int solveB(){
        Scanner in = new Scanner(System.in);
        int result = 0;
        int num = in.nextInt();
        int bNum = 0;
        while (num > 0){
            bNum = num%2;
            num = num/2;
            if (1 == bNum){
                result++;
            }
        }
        return result;
    }

    public static TreeNode construct(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd) {
        //边界输入处理
        if (null == preOrder || null == inOrder || 0 > preStart || 0 > inStart || preEnd >= preOrder.length || inEnd >= inOrder.length) {
            return null;
        }
        //根据先遍历获取root
        TreeNode rootNode = new TreeNode(preOrder[preStart]);
        //中序遍历根据root
        int rootInOrderIndex = inStart;
        while (rootInOrderIndex < inOrder.length && inOrder[rootInOrderIndex] != rootNode.data) {
            rootInOrderIndex++;
        }

        if (rootInOrderIndex >= inOrder.length) {
            return null;
        }
        //区分左右子树
        int leftTreeLength = rootInOrderIndex;
        int rightTreeLength = inEnd - rootInOrderIndex;
        //递归调用获得左右结点
        if (leftTreeLength > 0) {
            rootNode.leftNode = construct(preOrder, inOrder, preStart + 1, preStart+leftTreeLength, 0, leftTreeLength-1);
        }
        if (rightTreeLength > 0) {
            rootNode.rightNode = construct(preOrder, inOrder, preEnd-rightTreeLength+1, preEnd, leftTreeLength + 1, inEnd);
        }

        return rootNode;
    }

    public static TreeNode construct2(int[] preOrder, int[] inOrder) {
        //边界输入处理
        if (null == preOrder || null == inOrder || 0 == preOrder.length || 0 == inOrder.length) {
            return null;
        }
        if (preOrder.length != inOrder.length) {
            return null;
        }
        //根据先遍历获取root
        TreeNode rootNode = new TreeNode(preOrder[0]);
        //中序遍历根据root
        int rootInOrderIndex = 0;
        while (rootInOrderIndex < inOrder.length && inOrder[rootInOrderIndex] != rootNode.data) {
            rootInOrderIndex++;
        }

        if (rootInOrderIndex >= inOrder.length) {
            return null;
        }
        //区分左右子树
        int leftTreeLength = rootInOrderIndex;
        int rightTreeLength = inOrder.length - 1 - rootInOrderIndex;
        //递归调用获得左右结点
        int[] leftPreOrder = new int[leftTreeLength];
        int[] rightPreOrder = new int[rightTreeLength];
        System.arraycopy(preOrder, 1, leftPreOrder, 0, leftTreeLength);
        System.arraycopy(preOrder, leftTreeLength + 1, rightPreOrder, 0, rightTreeLength);
        int[] leftInOrder = new int[leftTreeLength];
        int[] rightInOrder = new int[rightTreeLength];
        System.arraycopy(inOrder, 0, leftInOrder, 0, leftTreeLength);
        System.arraycopy(inOrder, leftTreeLength + 1, rightInOrder, 0, rightTreeLength);
        if (leftTreeLength > 0) {
            rootNode.leftNode = construct2(leftPreOrder, leftInOrder);
        }
        if (rightTreeLength > 0) {
            rootNode.rightNode = construct2(rightPreOrder, rightInOrder);
        }
        return rootNode;
    }

    //后序遍历 左右中
    private static void postOrderTraverse1(TreeNode root){
        if (null != root){
            postOrderTraverse1(root.leftNode);
            postOrderTraverse1(root.rightNode);
            System.out.print(root.data+" ");
        }
    }

static class TreeNode {
    int data;
    TreeNode leftNode;
    TreeNode rightNode;

    TreeNode(int data) {
        this.data = data;
    }
}*/

//-----------拼多多--------------
/*public static int[][] lengthMatrix;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //行
        int row = in.nextInt();
        //列
        int col = in.nextInt();
        int[][] numMatrix = new int[col][row];
        lengthMatrix = new int[col][row];
        for (int r=0; r<row; r++){
            for (int c=0; c<col; c++){
                numMatrix[c][r] = in.nextInt();
            }
        }
        solveMaxLength(numMatrix);
    }

    public static void solveMaxLength(int[][] numMatrix){

        int maxLength = 0;
        for (int r=0; r<numMatrix[0].length; r++){//y
            for (int c=0; c<numMatrix.length; c++){//x
                int m = getMaxLength(numMatrix, c, r);
                maxLength = Math.max(maxLength, m);
            }
        }
        System.out.println(maxLength);
    }

    public static int getMaxLength(int[][] numMatrix, int x, int y){
        if (x>=numMatrix.length || y>=numMatrix[0].length || x<0 || y<0){
            return 0;
        }
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        //判断上边
        if (y-1>=0 && numMatrix[x][y-1] < numMatrix[x][y]){

            up = 0 != lengthMatrix[x][y-1] ? lengthMatrix[x][y-1] : getMaxLength(numMatrix, x, y-1);
        }
        //判断下边
        if (y+1<numMatrix[0].length && numMatrix[x][y+1] < numMatrix[x][y]){
            down = 0 != lengthMatrix[x][y+1] ? lengthMatrix[x][y+1] : getMaxLength(numMatrix, x, y+1);
        }
        //判断左边
        if (x-1>=0 && numMatrix[x-1][y] < numMatrix[x][y]){
            left = 0 != lengthMatrix[x-1][y] ? lengthMatrix[x-1][y] : getMaxLength(numMatrix, x-1, y);
        }
        //判断右边
        if (x+1<numMatrix.length && numMatrix[x+1][y] < numMatrix[x][y]){
            right = 0 != lengthMatrix[x+1][y] ? lengthMatrix[x+1][y] : getMaxLength(numMatrix, x+1, y);
        }

        //本身
        return lengthMatrix[x][y] = Math.max(Math.max(up, down), Math.max(left, right))+1;
    }*/

