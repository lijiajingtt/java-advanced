package week1;

public class ThreadTest extends Thread {
    private int count = 10;
    //重写run（）方法
    public void run(){
        while (true){
            System.out.print(count+"   ");
            //使count变量自减，当减为0时，退出循环
            if (--count == 0){
                break;
            }
        }
    }

    public static void main(String[] args) {
        //创建线程对象
        ThreadTest test = new ThreadTest();
        //启动线程
        test.start();
    }
}
