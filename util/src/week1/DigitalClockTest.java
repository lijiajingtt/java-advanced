package week1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class DigitalClockTest{
    public static void main(String[] args){
        JFrame jf = new JFrame("Digital Clock");
        JLabel jlblClock = new JLabel("time");

        jlblClock.setHorizontalAlignment(JLabel.CENTER);//设置Label中文件居中显示
        jf.add(jlblClock,"Center");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationByPlatform(true);
        jf.pack();
        jf.setVisible(true);

        MyThread mt = new MyThread(jlblClock);
        mt.start();
    }
}
class MyThread extends Thread{
    private JLabel clock;

    //得到当前的时期和时间
    public String getTime(){
        Calendar c = new GregorianCalendar();
        String time = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DATE)+" ";
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);
        int s = c.get(Calendar.SECOND);
        String ph = h<10 ? "0":"";
        String pm = m<10 ? "0":"";
        String ps = s<10 ? "0":"";
        time+= ph+h+":"+pm+m+":"+ps+s;
        return time;
    }
    //将日期和时间显示在lable上面并设置 1 秒刷新一次
    public MyThread(JLabel clock){
        this.clock = clock;
    }
    @Override
    public void run(){
        while(true){
            clock.setText(this.getTime());
            try{
                Thread.sleep(1000);//设置时间间隔为1000ms
            }
            catch(Exception e){
                System.out.println(e);
            }

        }
    }
}
