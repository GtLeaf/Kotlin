package interviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
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
        }
    }
}

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