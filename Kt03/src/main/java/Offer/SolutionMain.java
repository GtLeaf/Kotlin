package Offer;

import java.util.*;

public class SolutionMain {
    public static void main(String[] arhs){
        MyCollision collision = new MyCollision();
        Solutions solutions = new Solutions();
        TestCase testCase = new TestCase();
        ListNode listNode = collision.generate(2);
//        solutions.deleteNode(listNode, listNode.next);
//        solutions.ReorderOddEven(new int[]{1, 2, 3, 4, 5, 6});
//        solutions.spiralOrderPrint(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}, {13,14,15,16}});
//        solutions.printOrderZhiWord(new int[][]{{1,2},{3,4},{5,6}});
//        solutions.cutGold(new int[]{10,20,30});
//        solutions.findMaximizedCapital(3, 20, new int[]{7,8,60}, new int[]{5,10,100} );
//        solutions.testTireTree();
//        solutions.solveMaxSeachTree(testCase.getSearchTreeCase());
//        solutions.solveTreeMaxDistance(testCase.getSearchTreeCase());
        solutions.solveJudgeBSTree();

    }
}

class Solutions {
    //------机器人走格子-----
    //开始20:21
    public static int solveMachine(){
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        int count = 0;
        for (int i=0; i<n; i++){
            int tempN = i;
            int nCount = 0;
            if (tempN > 9 ){
                while (tempN > 0){
                    nCount += tempN%10;
                    tempN = tempN/10;
                }
            }else {
                nCount = tempN;
            }



            for(int j=0; j<m; j++){
                int mCount = 0;
                int tempM = j;
                if (tempM > 9){
                    while (tempM > 0){
                        mCount += tempM%10;
                        tempM = tempM/10;
                    }
                }else {
                    mCount = tempM;
                }

                if ((mCount + nCount) <= k){
                    count++;
                }else {
                    break;
                }
            }
        }

        return count;
    }

    //-------判断平衡二叉树--------
    //开始19:40
    //结束20:10
    //耗时30分钟
    //存在问题：数组越界
    //测试用例：10,5,15,3,7,13,18,
    public void solveJudgeBSTree(){
        Scanner in = new Scanner(System.in);
        ArrayList<TreeNode> nodeList = new ArrayList<>();
        String input = in.next();
        if (null == input || input.equals(",")){
            System.out.println("False");
            return;
        }
        String[] numStrs = input.split(",");
        for (String num : numStrs){
            if (!"".equals(num)){
                nodeList.add(new TreeNode(Integer.valueOf(num)));
            }
        }
        TreeNode curNode;
        for (int i=0; i<nodeList.size(); i++){
            curNode = nodeList.get(i);
            if (i*2+1 < nodeList.size()){
                curNode.left = nodeList.get(i*2+1);
            }
            if (i*2+2 < nodeList.size()){
                curNode.right = nodeList.get(i*2+2);
            }
        }
        System.out.println(judgeBSTree(nodeList.get(0)).isBS);
    }

    class BSTreeReturnType{
        int max;
        int min;
        boolean isBS;

        public BSTreeReturnType(int max, int min, boolean isBS) {
            this.max = max;
            this.min = min;
            this.isBS = isBS;
        }
    }

    public BSTreeReturnType judgeBSTree(TreeNode curNode){
        //空节点是搜索二叉树
        if (null == curNode){
            return new BSTreeReturnType(Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        }

        //所需信息，最大max，最小min,是否是搜索
        //情况1：左边是
        //情况2：右边是
        //情况3：左+右+自身都是
        BSTreeReturnType leftReturn = judgeBSTree(curNode.left);
        BSTreeReturnType rightReturn = judgeBSTree(curNode.right);
        boolean isBS = false;

        if (!leftReturn.isBS || !rightReturn.isBS){
            return new BSTreeReturnType(0, 0, false);
        }
        if (curNode.value > leftReturn.max
        && curNode.value < rightReturn.min){
            isBS = true;
        }
        //解黑盒
        return new BSTreeReturnType(Math.max(Math.max(leftReturn.max, rightReturn.max), curNode.value),
                Math.min(Math.min(leftReturn.min, rightReturn.min), curNode.value), isBS);
    }

    //-------聚会，上下级领导，多叉树，最大活跃度
    //开始14:46
    //结束15:09(未测试)
    //耗时23分钟
    class ActivelyNode{
        int actively;
        List<ActivelyNode> activelyNodes;
        ActivelyNode(int actively){
            this.actively = actively;
            activelyNodes = new ArrayList<>();
        }
    }

    class ActivelyReturnType{
        int arriveActively;
        int unarriveActively;

        public ActivelyReturnType(int arriveActively, int unarriveActively) {
            this.arriveActively = arriveActively;
            this.unarriveActively = unarriveActively;
        }
    }

    public void solveMaxActively(ActivelyNode root){
        ActivelyReturnType returnType = getMaxActively(root);
        System.out.println(Math.max(returnType.arriveActively, returnType.unarriveActively));
    }

    public ActivelyReturnType getMaxActively(ActivelyNode curNode){
        //需要信息：该节点来的最大活跃度，不来的最大活跃度
        int arriveActively = curNode.actively;
        int unarriveActively = 0;

        //遍历每一个后代，求来与不来的最大活跃度
        //1.本节点来 = 本节点活跃度 + 后代不来的最大活跃度
        //2.本节点不来 = Max(后代节点来，后代节点不来)
        for (int i=0; i<curNode.activelyNodes.size(); i++){
            ActivelyReturnType returnType = getMaxActively(curNode.activelyNodes.get(i));
            arriveActively += returnType.unarriveActively;
            unarriveActively += Math.max(returnType.arriveActively, returnType.unarriveActively);
        }

        return new ActivelyReturnType(arriveActively, unarriveActively);
    }
    //---------找一棵树的最远距离----------
    //开始12:43
    //结束13:08
    //耗时25分钟
    class TreeMaxDistance{
        //需要信息:1.单边最长 2.总长
        int maxEdge;
        int maxDistance;

        public TreeMaxDistance(int maxEdge, int maxDistance) {
            this.maxEdge = maxEdge;
            this.maxDistance = maxDistance;
        }
    }

    public void solveTreeMaxDistance(TreeNode root){

        System.out.println(findTreeMaxDistance(root).maxDistance);
    }

    public TreeMaxDistance findTreeMaxDistance(TreeNode curNode){
        if (null == curNode){
            return new TreeMaxDistance(0, 0);
        }
        //情况1：左边最大
        //情况2：右边最大
        //情况3: 左边最深+右边最深+自身最大
        TreeMaxDistance leftReturn = findTreeMaxDistance(curNode.left);
        TreeMaxDistance rightReturn = findTreeMaxDistance(curNode.right);

        //解黑盒
        int maxDistance = Math.max(leftReturn.maxEdge+rightReturn.maxEdge+1,
                Math.max(leftReturn.maxDistance, rightReturn.maxDistance));
        int maxEdge = Math.max(leftReturn.maxEdge, rightReturn.maxEdge)+1;

        return new TreeMaxDistance(maxEdge, maxDistance);
    }

    //--------找一棵树的最大搜索子树------
    //开始10:42
    //结束11:15 (未测试)
    //耗时33分钟
    class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    class SearchTreeReturnType{
        TreeNode head;
        int size;
        int max;
        int min;

        public SearchTreeReturnType(TreeNode head, int size, int max, int min) {
            this.head = head;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }


    public void solveMaxSeachTree(TreeNode head){
        System.out.println(findMaxSeachTree(head).size);
    }

    //测试用例
    public SearchTreeReturnType findMaxSeachTree(TreeNode curHead){
        //所需数据：1.最大子树头结点 2.最大子树size 3.最大值max 4.最小值min
        if (null == curHead){
            return new SearchTreeReturnType(null, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        SearchTreeReturnType leftReturn = findMaxSeachTree(curHead.left);
        SearchTreeReturnType rightReturn = findMaxSeachTree(curHead.right);

        //三种情况
        //1.最大子树在左边
        //2.最大子树在右边
        //3.最大子树是自己
        //情况1,2：
        int maxSize = leftReturn.size > rightReturn.size ? leftReturn.size : rightReturn.size;
        //情况3
        int includeSelfSize = 0;
        if (curHead.left == leftReturn.head
            && curHead.right == rightReturn.head
            && curHead.value > leftReturn.max
            && curHead.value < rightReturn.min){
            includeSelfSize = leftReturn.size + 1 + rightReturn.size;
        }
        maxSize = Math.max(maxSize, includeSelfSize);

        //解黑盒
        TreeNode head = leftReturn.size > rightReturn.size ? leftReturn.head : rightReturn.head;
        if (maxSize == includeSelfSize){
            head = curHead;
        }
        return new SearchTreeReturnType(head, maxSize,
                Math.max(Math.max(leftReturn.max, rightReturn.max), curHead.value),
                Math.min(Math.min(leftReturn.min, rightReturn.min), curHead.value));
    }


    //-------------tire树，
    // 开始 10:42,
    // 结束:11.09，
    // 耗时：27分钟，
    //完成度：insert()和search()
    class TireNode{
        int path;
        int end;
        TireNode[] tireNodes;

        public TireNode(int path, int end) {
            this.path = path;
            this.end = end;
            this.tireNodes = new TireNode[26];
        }
    }

    class Tire{
        private TireNode root;

        Tire(){
            root = new TireNode(0,0);
        }

        public void insert(String word){
            if (0 == word.length()){
                return;
            }
            char[] charArr = word.toCharArray();
            TireNode curNode = root;
            int index = 0;
            //遍历charArr判断，建立tire树
            for (char ch : charArr) {
                //求index
                index = ch - 'a';
                //建边
                if (null == curNode.tireNodes[index] ){
                    curNode.tireNodes[index] = new TireNode(0,0);
                }
                curNode = curNode.tireNodes[index];
                curNode.path++;
            }
            curNode.end++;
        }

        public int search(String word){
            if (0 == word.length()){
                return 0;
            }
            char[] charArr = word.toCharArray();
            int index;
            TireNode curNode = root;
            //遍历charArr，如果下一个为null，返回0,否则返回end
            for (char ch : charArr){
                index = ch - 'a';
                if (null == curNode.tireNodes[index]){
                    return 0;
                }
                curNode = curNode.tireNodes[index];
            }
            return curNode.end;
        }
    }
    //测试用例: cmd, cm, cing, cmd, cmd
    public void testTireTree(){
        Tire tire = new Tire();
        String[] words = new String[]{"cmd", "cm", "cing", "cmd", "cmd"};
        for (String str : words) {
            tire.insert(str);
        }

        System.out.println(tire.search("cmd"));
    }
    //-------------项目和利润，开始23:26,结束：23.46，耗时:20分钟 ---------------------
    class ProjectNode {
        int profit;
        int captital;

        public ProjectNode(int profit, int captital) {
            this.profit = profit;
            this.captital = captital;
        }
    }
    //captital建小根堆
    class MinComparator implements Comparator<ProjectNode> {
        @Override
        public int compare(ProjectNode o1, ProjectNode o2) {
            return o1.captital - o2.captital;
        }
    }

    //profits建大根堆
    class MaxComparator implements Comparator<ProjectNode> {
        @Override
        public int compare(ProjectNode o1, ProjectNode o2) {
            return o2.profit - o1.profit;
        }
    }
    public void findMaximizedCapital(int k, int m, int[] profits, int[] captital){
        ProjectNode[] projectNodes = new ProjectNode[profits.length];
        for (int i=0; i<profits.length; i++){
            projectNodes[i] = new ProjectNode(profits[i], captital[i]);
        }
        //captital建小根堆
        PriorityQueue<ProjectNode> captitalQueue = new PriorityQueue<>(new MinComparator());
        //profits建大根堆
        PriorityQueue<ProjectNode> profitQueue = new PriorityQueue<>(new MaxComparator());

        captitalQueue.addAll(Arrays.asList(projectNodes).subList(0, captital.length));
        while(true){
            //从captital中解锁项目，解锁加入profit堆
            while (!captitalQueue.isEmpty() && captitalQueue.peek().captital < m){
                profitQueue.add(captitalQueue.poll());
            }
            //如果无项目可以做，跳出
            if (profitQueue.isEmpty() || k<=0){
                break;
            }
            //从profit中取利润最大的做
            m += profitQueue.poll().profit;
            k--;
        }
        System.out.println(m);
    }

    //------------切割黄金,开始23:03,结束:23:16，耗时13分钟---------------------------
    public void cutGold(int[] numArr){

        //创建小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : numArr) {
            queue.add(num);
        }
        int result = 0;
        int cur = 0;
        while (queue.size()>1){
            //每次弹出最小两个数，相加
            cur = queue.poll() + queue.poll();
            //将两数之和加入堆
            result += cur;
            queue.add(cur);
        }
        System.out.println(result);
    }

    //-------------之字打印，开始23:16-23:35
    public void printOrderZhiWord(int[][] matrix){
        if (null == matrix){
            return;
        }

        int x = matrix.length-1;
        int y = matrix[0].length-1;
        int times = x>y ? x : y;
        boolean direct = true;
        for (int i=0; i<=times; i++){
            printZhiWord(matrix, i, direct);
            direct  = !direct;
        }

    }

    public void printZhiWord(int[][] m, int len, boolean direct){
        int curX = 0;
        int curY = 0;
        //从上往下
        if (direct){
            curX = len;
            while (curX>=0 && curY>=0){
                if (curX<m.length && curY<m[0].length){
                    System.out.println(m[curX--][curY++]);
                }
            }
        }else {//从下往上
            curY = len;
            while (curX>=0 && curY>=0){
                if (curX<m.length && curY<m[0].length){
                    System.out.println(m[curX++][curY--]);
                }
            }
        }


    }

    //-------------蛇形打印,开始:13:38--------------------
    public void spiralOrderPrint(int[][] matrix){
        if (null == matrix ){
            return;
        }
        int x1 = 0;
        int y1 = 0;
        int x2 = matrix.length-1;
        int y2 = matrix[0].length-1;
        while(x1 <= x2 && y1 <= y2){
//            printEdge(matrix, x1++, y1++, x2--, y2--);
            rotateEdge(matrix, x1++, y1++, x2--, y2--);
        }
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++ ){
                System.out.print(matrix[j][i]+" ");
            }
            System.out.println();
        }
        System.out.println("结束");
    }

    //可改递归
    private void printEdge(int[][] m,int x1, int y1, int x2,int y2){
        if (x1 > x2 && y1 > y2){
            return;
        }
        //只有一横
        if (y1 == y2){
            for (int i=x1; i<=x2; i++){
                System.out.print(m[i][y1]+"  ");
            }
        }else if(x1 == x2){//在同一列
            for (int i=y1; i<=y2; i++){
                System.out.println(m[x1][i]);
            }
        }else {
            int curX = x1;
            int curY = y1;
            //打印上
            while (curX < x2){
                System.out.print(m[curX++][curY]+"  ");
            }
            //打印右
            while(curY < y2){
                System.out.print(m[curX][curY++]+"  ");
            }
            //打印下
            while(curX > x1){
                System.out.print(m[curX--][curY]+"  ");
            }
            //打印左
            while(curY > y1){
                System.out.print(m[curX][curY--]+"  ");
            }
        }
    }


    public void rotateEdge(int [][] m,int x1, int y1, int x2,int y2){
        int times = x2 - x1;
        for (int offset = 0; offset < times; offset++){
            int temp = m[x1+offset][y1];
            m[x1+offset][y1] = m[x1][y2-offset];
            m[x1][y2-offset] = m[x2-offset][y2];
            m[x2-offset][y2] = m[x2][y1+offset];
            m[x2][y1+offset] = temp;
        }
    }

    //----------------比较器
    public void sort(){
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        List<ListNode> listNodeList = new ArrayList<>();
        listNodeList.add(listNode3);
        listNodeList.add(listNode1);
        listNodeList.add(listNode2);

        listNodeList.sort((Comparator<ListNode>) (o1, o2) -> {
            if (o1.val < o2.val) return -1;
            if (o1.val == o2.val) return 0;
            //if (o1.val > o2.val)
            return 1;
        });
        System.out.println("finish");
    }

    //-------------------将数组按一定规律划分前后，开始15：15，结束15:40---------------------------
    public void ReorderOddEven(int arr[]){
        //边界处理
        if (null == arr || arr.length<=1){
            return;
        }

        int left = 0;
        int right = arr.length-1;

        while (left<right){
            //不符合在前面才进行交换
            while (left<right && isEven(arr[left])){
                left++;
            }
            //符合在前面，则不符合在后面，交换
            while (left<right && !isEven(arr[right])){
                right--;
            }
            if (left<right){
                arr[left] = arr[left]^arr[right];
                arr[right] = arr[left]^arr[right];
                arr[left] = arr[left]^arr[right];
            }
        }

        System.out.println("");
    }

    //判断是否在数组前面符合条件
    public boolean isEven(int n){

        return n%2 == 1;
    }


    //删除链表指定节点，要求O(1),需确保val不同
    public ListNode deleteNode(ListNode listNode, ListNode node){
        //边界处理，null，node不在listNode中不考虑
        if (null == listNode || null == node){
            return null;
        }
        ListNode point = listNode;

        //只有一个结点的情况
        if (node.val == listNode.val){
            return null;
        }else if (null == node.next){//删除最后一个节点，找他的前一个节点
            while (point.next != null && point.next.val != node.val ){
                point = point.next;
            }
            //node不在listNode
            if (null == point.next){
                return listNode;
            }
            //找到，删除node
            ListNode tempNode = point.next;
            point.next = tempNode.next;
            tempNode.next = null;
            return listNode;
        }else {
            //不考虑node不在listNode的情况
            ListNode temp = node.next;
            node.val = temp.val;
            node.next = temp.next;
            temp.next = null;
            return listNode;

        }
    }

    //-----------------------------------
    //打印1到最大的n位数的主方法
    public void printToMaxOfDigits(int n){
        if(n <= 0){
            System.out.println("输入的n没有意义");
            return;
        }
        char number[] = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }
        for (int i = 0; i < 10; ++i) {
            number[0] = (char) (i + '0');
            printToMaxOfNDigitsRecursively(number, n, 0);
        }
    }
    //利用递归实现1到最大的n位数的全排列
    public void printToMaxOfNDigitsRecursively(char[] number, int n, int index) {
        if(index == n - 1){
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; ++i) {
            number[index + 1] = (char) (i + '0');
            printToMaxOfNDigitsRecursively(number, n, index + 1);
        }
    }

    //输出
    private void printNumber(char[] number) {
        boolean isBeginning0 = true;
        int nLength = number.length;
        for (int i = 0; i < nLength; ++i) {
            if(isBeginning0 && number[i]!='0'){
                isBeginning0 = false;
            }
            if(!isBeginning0){
                System.out.print(number[i]);
            }
        }
        System.out.println();
    }
}

class ListNode{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

//-----------------------随机链表生成器-------------------
interface  Collision<T>{
    T generate();
    boolean equals();
    T rightMethod();
}
class MyCollision {
    //产生一条长度随机，值随机的链表
    public ListNode generate(){
        int count = (int) (Math.random()*10);
        ListNode rootNode = new ListNode(0);
        ListNode tempNode = rootNode;
        for (int i=0; i<count; i++){
            tempNode.next = new ListNode((int) (Math.random()*10));
            tempNode = tempNode.next;
        }
        return rootNode.next;
    }

    //产生一条长度为count，值随机的链表
    public ListNode generate(int count){
        ListNode rootNode = new ListNode(0);
        ListNode tempNode = rootNode;
        for (int i=0; i<count; i++){
            tempNode.next = new ListNode((int) (Math.random()*10));
            tempNode = tempNode.next;
        }
        return rootNode.next;
    }

    //判断两条链表是否相等,长度，每个节点的值
    public boolean equals(ListNode listNode1, ListNode listNode2){
        //边界处理
        if (null == listNode1 && null == listNode2){
            return true;
        }else if (null == listNode1 || null == listNode2){
            return false;
        }

        //值相等，next不为空
        while (listNode1.val == listNode2.val
                && listNode1.next != null
                && listNode2.next != null){
            listNode1 = listNode1.next;
            listNode2 = listNode2.next;
        };
        //判断值是否相等
        if (listNode1.val != listNode2.val){
            return false;
        }
        //判断长度是否相等
        return null == listNode1.next && null == listNode2.next;
    }

    public ListNode rightMethod(ListNode list, ListNode node){
        //边界处理,list == null, node == null, node不在list中
        if (null == list){
            return null;
        }
        if (null == node){
            return list;
        }
        ListNode point = list;
        while (point.val != node.val && point.next != null && point.next.val != node.val){
            point = point.next;
        }
        //第一个节点是需要删除的
        if (point.val == node.val){
            list = list.next;
        }
        //point.next == null，则node不在list上
        if (point.next == null){
            return list;
        }
        //next就是node
        if (point.next.val == node.val){
            ListNode tempNode = point.next;
            point.next = tempNode.next;
            tempNode.next = null;
        }

        return list;
    }

}


