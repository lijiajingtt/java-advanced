package week1;

import javax.swing.*;
import java.awt.*;

public class CircleFrame extends JFrame {
    private  JLabel txtLabel;
    public CircleFrame() {
        init();
        setTitle("绘制同心圆");
        setSize(1000, 800);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void init() {
        try {
            txtLabel = new JLabel();
            Font font = new Font("微软雅黑",Font.BOLD,21);
            txtLabel.setFont(font);
            TxtThread thread = new TxtThread();
            thread.setTxtLabel(txtLabel);
            new Thread(thread).start();
            //同心圆线程
            CircleThread circleThread = new CircleThread();
            circleThread.setFrame(this);
            new Thread(circleThread).start();

            add(txtLabel);
        }catch (Exception e){
            System.out.println("-1");
        }
    }

    public static void main(String[] args) {
        new CircleFrame();
    }
}

