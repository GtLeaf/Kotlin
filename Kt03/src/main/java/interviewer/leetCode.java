package interviewer;

import java.util.*;

public class leetCode {
    public static void main(String[] args){
        Solution solvable = new Solution();
        Solution solution = new Solution();
//        System.out.println(((SolutionMain) solvable).findGiftNumber());
//        ((SolutionMain) solvable).findGiftNumber();
//        ((SolutionMain) solvable).dealWordFunction();
//        ((SolutionMain) solvable).findRepeatNumber(new int[]{6, 3, 1, 0, 2, 5, 3});
//        solvable.mergeTwoLists2(solution.getTestListNode1(), solution.getTestListNode2());
//        solvable.mergeKLists(solution.getTestListNodeList());
//        System.out.println(solvable.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
            System.out.println("a".matches(".*"));
//            System.out.println(solution.isMatch("aabc", ".*b"));

    }
}

interface Solvable{
    Object solve(String str);
}

class Solution implements Solvable{

    @Override
    public Object solve(String str) {

        return romantoInteger(str);
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    //---------------------正则字符匹配(10困难),开始19:39,结束：21:11，耗时：1小时32分钟------------------------------
    public boolean isMatch(String s, String p) {
        //边界处理
        if ("".equals(p) && "".equals(s)){
            return true;
        }
        //正则为空，s不为空
        if ("".equals(p) ){
            return false;
        }


        return matchCode(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private boolean matchCode(char[] charsS, char[] charsP, int sIndex, int pIndex){
        //匹配完输出结果
        if (sIndex>=charsS.length && pIndex>=charsP.length){
            return true;
        }
        //正则结束了，s没有结束
        if(pIndex >= charsP.length){
            return false;
        }


        //判断第二个字符是否是*，且不越界 .*,a*
        if (pIndex+1<charsP.length && '*'==charsP[pIndex+1]){
            //如果字母符合，或者是'.'
            if(sIndex<charsS.length && (charsS[sIndex] == charsP[pIndex] || '.' == charsP[pIndex])){
                if (pIndex+2<charsP.length){
                    return matchCode(charsS, charsP, sIndex, pIndex+2) ||//忽略
                            matchCode(charsS, charsP, sIndex+1, pIndex) ||//本状态
                            matchCode(charsS, charsP, sIndex+1, pIndex+2);  //下一个状态
                }else {
                    return matchCode(charsS, charsP, sIndex+1, pIndex);
                }

            }else {
                return matchCode(charsS, charsP, sIndex, pIndex+2);
            }

        }
        //判断是否是'.'和其他字符
        if (sIndex<charsS.length && ('.' == charsP[pIndex] || charsS[sIndex] == charsP[pIndex])){
            return matchCode(charsS, charsP, sIndex+1, pIndex+1);
        }
        return false;
    }


    //------------------求和最大的子数组53题改进，开始12:22，结束12：31失败---------------------------------
    public int maxSubArray(int[] nums) {
        //边界处理
        if (null == nums){
            return 0;
        }
        if (1 == nums.length){
            return nums[0];
        }
        int max = 0;
        int left = 0;
        int right = nums.length-1;
        int sum = 0;
        for (int i=0; i<nums.length; i++){
            max += nums[i];
        }
        sum = max;
        while (left < right){
            if (nums[left] < nums[right]){
                sum -= nums[left];
                left++;
            }else {
                sum -= nums[right];
                right--;
            }
            max = sum > max ? sum : max;
        }

        return max;
    }

    //------------------求和最大的子数组53题，开始10：51,结束11:15，耗时24分钟-----------------------------
    public int maxSubArray2(int[] nums) {
        //边界处理
        if (null == nums){
            return 0;
        }
        if (1 == nums.length){
            return nums[0];
        }


        return getMaxSubArray(nums, 0, nums[0]);
    }
    public int getMaxSubArray(int[] nums, int index, int max){
        if (index > nums.length-1){
            return max;
        }
        int sum = nums[index];
        int left = index-1;
        int right = index+1;
        //当最大值为nums[index]的情况
        max = sum>max ? sum : max;
        //向左拓展
        while ( left>=0){
            sum += nums[left];
            max = sum>max ? sum : max;
            left--;
        }
        //向右拓展
        while (right<nums.length){
            sum += nums[right];
            max = sum>max ? sum : max;
            right++;
        }

        return getMaxSubArray(nums, index+1, max);
    }

    //--------------------------测试用例-----------------------
    public ListNode getTestListNode1(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;

        return node1;
    }

    public ListNode getTestListNode2(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;

        return node1;
    }
    public ListNode[] getTestListNodeList(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(6);
        node4.next = node5;
        node5.next = node6;

        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(6);
        node7.next = node8;

        ListNode[] listNodes = new ListNode[]{node1, node4, node7};

        return listNodes;
    }

    //-------------------------------合并K条有序链表，开始19:28 ，结束19:55，耗时27分钟---------------------------------------
    public ListNode mergeKLists(ListNode[] lists) {
        //边界处理
        if (0 == lists.length){
            return null;
        }
        if (1 == lists.length){
            return lists[0];
        }

        ListNode node = divideLists(lists, 0, lists.length-1);

        return node;
    }
    private ListNode divideLists(ListNode[] lists, int start, int end){
        //list数量大于2，继续划分，否则进行合并
        if (end - start > 1){
            int mid = (start+end)/2;
            return mergeTwoLists(divideLists(lists, start, mid), divideLists(lists, mid+1, end));
        }else if (end - start < 1){//start = end
            return lists[start];
        }else {//start-end = 1,比如2-1
            return mergeTwoLists(lists[start], lists[end]);
        }

    }
    //---------------------2条链表合并，16:30,18:10,结束：18:50，耗时40分钟------------------------------------
    //大神解法
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        //边界处理
        if (null == l1 && null == l2){
            return null;
        }else if (null == l1){
            return l2;
        }else if (null == l2){
            return l1;
        }
        ListNode rootNode ;
        ListNode curNode1 ;
        ListNode curNode2 ;
        ListNode preNode1 ;


        //确保cur1<cur2,以小的为主链
        if (l1.val < l2.val){
            rootNode = l1;
            curNode1 = l1;
            curNode2 = l2;
        }else {
            rootNode = l2;
            curNode1 = l2;
            curNode2 = l1;
        }
        preNode1 = curNode1;

        while (curNode2 != null){
            //从l1中找到第一个大于cur2的节点，合并
            while ( (curNode1.val <= curNode2.val) && curNode1.next != null){
                //nextNode1可能为空
                preNode1 = curNode1;
                curNode1 = curNode1.next;
            }
            //next1>cur2 && cur1.next == null
            //next1>cur2 && cur1.next != null
            while (curNode2 != null && curNode1.val > curNode2.val ){
                ListNode tempNode = curNode2.next;
                preNode1.next = curNode2;
                curNode2.next = curNode1;
                curNode2 = tempNode;
                preNode1 = preNode1.next;
            }
            //cur1<cur2 && cur1.next == null
            if (null == curNode1.next){
                curNode1.next = curNode2;
                //使while条件不满足，退出
                break;
            }
        }

        return rootNode;
    }


    //-------------------------------------------------------------------------

    //---------------------链表移除倒数第N个节点，开始15:38-------------------------
    public ListNode removeNthFromEnd(ListNode head, int n) {

        //边界处理
        if (null == head || null == head.next){
            return null;
        }

        int listLength = 1;

        //定义一个快指针，一个慢指针指向根节点
        ListNode fastNode = head;
        ListNode slowNode = head;
        //快指针先走N步
        for (int i=0; i<n && fastNode.next!= null; i++){
            fastNode = fastNode.next;
            listLength++;
        }
        //快慢指针同时走，直到末尾，此时慢指针所在节点即为倒数第N+1个
        while (fastNode.next != null){
            fastNode = fastNode.next;
            slowNode = slowNode.next;
            listLength++;
        }
        //移除慢指针指向节点
        if (listLength == n){
            head = head.next;
        }else {
            ListNode tempNode = slowNode.next;
            slowNode.next = tempNode.next;
            tempNode.next = null;
        }
        return head;
    }
    //--------------------16:15结束，耗时40分钟------------------------


    //-------------------罗马转数字，耗时：40分钟 ------------------------
    public int romantoInteger(String s){
        if ("".equals(s)){
            return 0;
        }
        int num = 0;
        Map<String, Integer> roman = new HashMap<>();
        roman.put("I", 1);
        roman.put("IV", 4);
        roman.put("V", 5);
        roman.put("IX", 9);
        roman.put("X", 10);
        roman.put("XL", 40);
        roman.put("L", 50);
        roman.put("XC", 90);
        roman.put("C", 100);
        roman.put("CD", 400);
        roman.put("D", 500);
        roman.put("CM", 900);
        roman.put("M", 1000);
        int index = 0;
        String tempStr1;
        String tempStr2;

        while (index < s.length()){
            if (index+1< s.length()){
                tempStr2 = s.substring(index, index+2);
                //如果包含，下标+2，sum增加
                if (roman.containsKey(tempStr2)){
                    num += roman.get(tempStr2);
                    //改变指针
                    index += 2;
                    continue;
                }
            }
            //划分出头2个字符
            tempStr1 = s.substring(index, index+1);

            if (roman.containsKey(tempStr1)){
                num += roman.get(tempStr1);
                index += 1;
            }
        }
        return num;
    }

    //------------------------最长前缀-------------------------
    public String longestCommonPrefix(String[] strs) {
        String prefix = "";

        Map<Character, Map> tire = new HashMap<>();
        //构建tire树
        for (int i=0; i<strs.length; i++){
            char[] strChars = strs[i].toCharArray();
            tire = buildTire(tire, strChars, 0);
        }

        return  prefix;
    }

    public Map<Character, Map> buildTire(Map<Character, Map> map, char[] strChars, int index){
        if (index < strChars.length){
            return map;
        }
        Map<Character, Map> nextMap = new HashMap<>();
        map.put(strChars[index], nextMap);

        buildTire(nextMap, strChars, index+1);
        return map;
    }

    public int findCoinNumber(){
        Scanner sc = new Scanner(System.in);
        int spent = sc.nextInt();
        int coinNumber = 0;
        //边界处理0, 1, 1024
        if(1024 == spent){
            return 0;
        }


        int money = 1024 - spent;
        //64的硬币数
        if (money >= 64){
            coinNumber += money/64;
            money = money%64;
        }
        //16的硬币数
        if (money >= 16){
            coinNumber += money/16;
            money = money%16;
        }
        //4的硬币数
        if (money >= 4){
            coinNumber += money/4;
            money = money%4;
        }
        //1的硬币数
        return coinNumber+money;
    }

    class People{
        int No;
        int gift = 1;

        public People(int no) {
            No = no;
        }
    }

    public void findGiftNumber(){
        Scanner sc = new Scanner(System.in);
        int thingNum = sc.nextInt();
        if (0 == thingNum){
            System.out.println(0);
            return;
        }
        for (int thing = 0; thing<thingNum; thing++){
            int peopleNum = sc.nextInt();
            //边界处理
            if (1 == peopleNum){
                System.out.println(1);
                return;
            }

            People[] peoples = new People[peopleNum];
            //数据接收
            for (int i=0; i<peopleNum; i++){
                peoples[i] = new People(sc.nextInt());
            }
            findGift(peopleNum, peoples);
        }

    }

    public void findGift(int peopleNum, People[] peoples){

        //判断每个人,注意边界处理
        for (int num=0; num<peopleNum; num++){
            //小于0移到末尾，大于最大长度移到0
            int leftPeople = num-1 >= 0 ? num-1 : peopleNum-1;
            int rightPeople = num+1 >= peopleNum ? 0 : num+1;
            //与左边比，小于左边，gift不变，等于左边:gift = left,比左边高，gift=left+1
            if (peoples[num].No == peoples[leftPeople].No  ){
//                peoples[num].gift = peoples[leftPeople].gift;
            }else if (peoples[num].No > peoples[leftPeople].No){
                peoples[num].gift = peoples[leftPeople].gift+1;
            }
            //与右边比，小于右边，gift不变，等于右边:gift = left,比右边高，gift=gift>right+1?gift:right+1
            if (peoples[num].No > peoples[rightPeople].No){
                peoples[num].gift = peoples[rightPeople].gift+1;
            }
        }

        //统计
        int sumGift = 0;
        for (People people : peoples){
            sumGift += people.gift;
        }
        System.out.println(sumGift);
    }

    class Cord{

    }

    public void cordLength(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] cords = new int[n];
        for (int i=0; i< n; i++){
            cords[i] = sc.nextInt();
        }
    }

    class TireNode{
        String str;
        int strLength;
        int strNum;
        TireNode[] tireNodes = new TireNode[26];
        TireNode preNode;
        public TireNode(String str, int strLength, int strNum) {
            this.str = str;
            this.strLength = strLength;
            this.strNum = strNum;
        }

        public TireNode(String str, int strLength, int strNum, TireNode preNode) {
            this.str = str;
            this.strLength = strLength;
            this.strNum = strNum;
            this.preNode = preNode;
        }
    }

    public void dealWordFunction(){
        Scanner sc = new Scanner(System.in);
        int wordNum = sc.nextInt();
        //边界处理
        if (0 == wordNum){
            return;
        }

        for (int i=0; i<wordNum; i++){
            TireNode root = new TireNode("", 1, 0);
            dealWord(sc.next(), root);
        }
    }

    public void dealWord(String string, TireNode node){
        StringBuffer fixStr = new StringBuffer();
        TireNode curNode = node;
        char[] chars = string.toCharArray();
        //组装，构造树
        for (int i=0; i<chars.length; i++){
            //处理与上一个相同
            if(curNode.str.equals(String.valueOf(chars[i]))){
                curNode.strNum++;
                if (curNode.strNum == 2 && curNode.preNode.strNum == 2){
                    //两对个去掉一个
                    curNode.strNum--;
                    continue;
                }
                //三个去掉一个
                if (curNode.strNum == 3){
                    curNode.strNum--;
                    continue;
                }
                fixStr.append(chars[i]);
            }else {//字母不同，产生下一个结点
                for (int j=0; j<26; j++){
                    if (null == curNode.tireNodes[j]){
                        curNode.tireNodes[j] = new TireNode(String.valueOf(chars[i]), curNode.strLength+1, 1, curNode);
                        fixStr.append(chars[i]);
                        curNode = curNode.tireNodes[j];
                        break;
                    }
                }
            }
        }

        System.out.println(fixStr);
    }

    //找出数组中任意一个重复数字
    public void findRepeatNumber(int arr[]){

        //边界处理
        if (arr.length <= 1){
            return;
        }
        for (int i = 0; i < arr.length; i++){
            if (arr[i]<0 || arr[i]>arr.length-1){
                return;
            }
        }

        for (int index = 0; arr.length > index; index++){
            int num = arr[index];
            //判断第一个数下标index和arr[index]是否相等，即是否在对应坐标
            if (arr[index] != index){
                //如果不相等，比较以num为下标的与num是否相等
                //如果相等，找到重复的数字
                if(arr[arr[num]] == num){
                    System.out.println(num);
                    return;
                }else {//如果不相等，交换位置,把num放到下标为numde的位置上
                    int temp = arr[index];
                    arr[index] = arr[num];
                    arr[num] = temp;
                }
            }
        }
    }

}