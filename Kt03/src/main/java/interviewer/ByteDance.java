package interviewer;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ByteDance {
    public static void main(String[] args){
        /*SectionMax sectionMax = new SectionMax();
        System.out.println(sectionMax.findSectionMax());*/

    }
}

class point{
    int x;
    int y;

    public point() { }

    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class Idea{
    int No;
    int upTime;
    int level;
    int needTime;

    public Idea() { }

    public Idea(int no, int upTime, int level, int needTime) {
        No = no;
        this.upTime = upTime;
        this.level = level;
        this.needTime = needTime;
    }

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public int getUpTime() {
        return upTime;
    }

    public void setUpTime(int upTime) {
        this.upTime = upTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNeedTime() {
        return needTime;
    }

    public void setNeedTime(int needTime) {
        this.needTime = needTime;
    }

    public void PMIdea(){
        Scanner sc = new Scanner(System.in);
        int PMnum = sc.nextInt();
        int engiM = sc.nextInt();
        int ideaNum = sc.nextInt();
        List<Idea> ideaList = new ArrayList<Idea>();
        //读入idea相关信息
        while (sc.hasNextInt()){
            ideaList.add(new Idea(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        //进行先后顺序排序
        Map ideaMap = new HashMap<Integer, Idea>();
        List<Idea> tempList = new ArrayList<Idea>();
        for (int i=1; i<ideaList.size(); i++){
            if (ideaList.get(i).getNo() == ideaList.get(i-1).getNo()){
                tempList.add(ideaList.get(i));
            }else {//如果PM序号不同

            }
        }
        //计算执行时间
    }
}

class PointNumber{

    public void getPonits(){
        Scanner sc = new Scanner(System.in);
        List<Point> pointList = new ArrayList();
        Map<Integer, Integer> pointMap = new HashMap();
        int pointNum = sc.nextInt();
        for (int num = 0; num<pointNum; num++){
            pointList.add(new Point(sc.nextInt(), sc.nextInt()));
        }

        for (int i=0; i<pointList.size(); i++){
            Point point = pointList.get(i);
            //接收x，如果同一个x存在，对比y，保留y大的
            if (pointMap.containsKey(point.x)){
                if (point.y > pointMap.get(point.x)){
                    pointMap.put(point.x, point.y);
                }
            }else {
                pointMap.put(point.x, point.y);
            }
        }

        for (Object o : pointMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

class SectionMax{
    public int findSectionMax(){
        Scanner sc = new Scanner(System.in);
        int max = 0;
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for (int i=0; i<n; i++){
            numbers[i] = sc.nextInt();
        }
        //以第j个点为最小点，向两边扩
        /*for (int j=0; j<n; j++){

            int left = j-1;
            int right = j+1;
            int sum = numbers[j];
            //向左扩
            while(left>=0 && numbers[j]<=numbers[left]){
                sum += numbers[left];
                left--;
            }
            //向右扩
            while (right<n && numbers[j]<=numbers[right]){
                sum += numbers[right];
                right++;
            }
            //求乘积
            int tempMax = sum*numbers[j];
            max = tempMax>max ? tempMax : max;
        }*/
        return max;
    }
}