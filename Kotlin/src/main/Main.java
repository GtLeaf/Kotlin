package main;

public class Main {
    public static void main(String... args){
        /*for (String arg : args) {
            System.out.println(arg);
        }*/

        for (String arg : args) {
            String[] argArr = arg.split("_");
            for (String s : argArr) {
                System.out.print(s);
                System.out.print(" ");
            }
        }
    }
}
