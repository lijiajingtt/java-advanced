package week1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Time extends JFrame implements Runnable{
    public static void main(String[] args) {
        new Thread(new Time()).start();
    }

    private static final long serialVersionUID = 1L;
    private JLabel date;
    private JLabel time;

    public Time(){
        // 初始化图形界面
        this.setVisible(true);
        this.setTitle("数字时钟");
        this.setSize(280, 180);
        this.setLocation(200, 200);
        this.setResizable(true);
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        // 时间
        time = new JLabel();
        time.setBounds(31, 54, 196, 59);
        time.setFont(new Font("Arial", Font.PLAIN, 50));
        panel.add(time);
        // 日期
        date = new JLabel();
        date.setFont(new Font("微软雅黑",Font.PLAIN,13));
        date.setBounds(47, 10, 180, 22);
        panel.add(date);
    }

    //用一个线程来更新时间
    @Override
    public void run() {
        while (true) {
            try {
                date.setText(new SimpleDateFormat("yyyy 年  MM 月 dd 日  EEEE").format(new Date()));
                time.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
