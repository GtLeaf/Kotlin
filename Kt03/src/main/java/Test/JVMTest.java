package Test;

public class JVMTest {

    private Object object;

    public static void main(String[] args){

        JVMTest jvmTest1 = new JVMTest();
        JVMTest jvmTest2 = new JVMTest();

        jvmTest1.object = jvmTest2;
        jvmTest2.object = jvmTest1;

        jvmTest1 = null;
        jvmTest2 = null;

        System.gc();
    }
}
