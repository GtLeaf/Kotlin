public class TestMain {
    public static void main(String[] args){
        String s1 = new String("java");
        String s2 = new String("java");
        String s3 = "java";

        A aa = new A();
        A aaa = new A();
        B bb = new B();

        System.out.println(s1 == s2);
        System.out.println(s1 .equals(s2));
        System.out.println(s1 == "java");
        System.out.println(s1.equals("java"));
        // == 对比的是内存地址
        // .equals对比的是值
        //"java"在方法区中，在字符串常量池内
    }
}

class A{

}

class B{

}