import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println(solveB());

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
}