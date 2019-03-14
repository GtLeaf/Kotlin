package interviewer;

public class Synchronized {
    public static void main(String[] args){
        TicketSell sell1 = new TicketSell();
        TicketSell sell2 = new TicketSell();
        new Thread(sell1::sell4).start();
        new Thread(sell2::sell4).start();
    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        for (int i=0; i<4; i++){
            System.out.println(i);
        }
    }
}

class TicketSell{
    static int ticket = 10;
    //对象锁，方法锁
    public synchronized void sell(){
        for (int i=0; i<4 && ticket>0; i++){
            ticket--;
            System.out.println(Thread.currentThread()+"销售了第"+ticket+"张票");
        }
    }
    //对象锁，代码块锁
    public void sell2(){
        synchronized(this){
            for (int i=0; i<4 && ticket>0; i++){
                ticket--;
                System.out.println(Thread.currentThread()+"销售了第"+ticket+"张票");
            }
        }
    }
    //类锁，静态方法锁
    public static synchronized void sell3(){
        for (int i=0; i<4 && ticket>0; i++){
            ticket--;
            System.out.println(Thread.currentThread()+"销售了第"+ticket+"张票");
        }
    }
    //类锁
    public void sell4(){
        //传入类对象为类锁，同一个类所有实例调用这个方法是都要先持有锁
        synchronized (TicketSell.class){
            for (int i=0; i<4 && ticket>0; i++){
                ticket--;
                System.out.println(Thread.currentThread()+"销售了第"+ticket+"张票");
            }
        }
    }
}

class Test{
    public synchronized void function1(){}              //对象锁，方法锁
    public synchronized static void function2(){}       //类锁
    public void function3(){synchronized (this){}}      //对象锁
    public void function4(){synchronized (Test.class){}}//类锁
}