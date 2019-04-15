package Test;
class Zi extends Fu{
    int num = 2;

    static {
        System.out.println("Zi static code");
    }

    {
        System.out.println("Zi code + num:" + num);
    }
    public Zi(){
        System.out.println("Zi GouZao ");
    }

}
class Fu{
    int fuNum = 3;
    static {
        System.out.println("Fu static code");
    }
    {
        System.out.println("Fu code + fuNum:" + fuNum);
    }
    public Fu(){
        System.out.println("Fu GouZao");
    }
}
public class Test {
    public static void main(String[] args){
        Zi zi = new Zi();
//        int currentNumber = NumberTest.numberTest.currentNumber;
    }
}

class NumberTest{
    //static语句顺序执行，执行new NumberTest(6)时，value为0
    static NumberTest numberTest = new NumberTest(6);
    static int value = 20;
    int currentNumber;
    static {
        System.out.println("static code");
    }
    NumberTest(int currentN){
        currentNumber = value - currentN;
        System.out.println("currentNumber："+currentNumber);
    }
    NumberTest(String str){
        System.out.println("成员变量初始化"+str);
    }
}