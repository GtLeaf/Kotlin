package interviewer;

public class OuterJava {

    int flag = 1;


    class InnerJava{

    }
    OuterJava(){
        System.out.println("父类构造方法");
        test();
    }
    void test(){
        System.out.println("Super.test()");
    }
}

class Son extends OuterJava{
    Son(int i){
        flag = i;
        System.out.println("子类构造方法"+flag);
        System.out.println("Sub.Sub()flag="+flag);
    }
    void test(){
        System.out.println("Sub.test()flag="+flag);
    }
}
