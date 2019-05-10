package week1;

import javax.swing.*;
public class TimeTest2 implements Runnable {

    private Thread timeTest2Thread;

    public void setTimeTest2Thread(
            Thread timeTest2Thread){
        this.timeTest2Thread = timeTest2Thread;
    }
    private JLabel numberLabel;
    public void setNumberLabel(JLabel numberLabel){
        this.numberLabel = numberLabel;
    }
    @Override
    public void run() {
        numberLabel.setText("倒计时开始");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            try {
                timeTest2Thread.join();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        for (int i = 10;i>0;i--){
            numberLabel.setText(String.valueOf(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numberLabel.setText("节目开始！");
    }
}