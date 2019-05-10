package week1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TimeFrame extends JFrame {
    private JLabel numberLabel;
    public TimeFrame() {
        init();
        setTitle("倒计时窗体");
        setLocationRelativeTo(null);
        setSize(1000,900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void init() {
        setLayout(null);
        ShadePanel1 shadePanel1 = new ShadePanel1();
        shadePanel1.setLayout(null);
        shadePanel1.setBounds(0, 0, 1000,900);
        ImgPanel1 imgPanel1 = new ImgPanel1();
        imgPanel1.setLayout(null);
        imgPanel1.setImgPath("1.jpg");
        imgPanel1.setBounds(280, 180, 600, 400);
        numberLabel = new JLabel();
        numberLabel.setBounds(180, 150, 300, 100);
        Font font = new Font("微软雅黑", Font.PLAIN, 60);
        numberLabel.setFont(font);
        imgPanel1.add(numberLabel);
        shadePanel1.add(imgPanel1);
        add(shadePanel1);
        TimeTest2 timeTest2 = new TimeTest2();
        Thread timeTest2Thread = new Thread(timeTest2);
        timeTest2Thread.start();
        timeTest2.setNumberLabel(numberLabel);

    }
    public static void main(String[] args) {
        new TimeFrame();
    }
}
/**
 * 继承JPanel的自定义面板，实现渐变背景色，paintComponent重绘组件
 */
class ShadePanel1 extends JPanel {

    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        //创建填充模式对象
        GradientPaint paint = new GradientPaint(0, 0, Color.BLUE, 0, height, Color.white);
        g.setPaint(paint);
        g.fillRect(0, 0, width, height);
    }
}
/**
 * 继承JPanel的自定义面板，绘制背景图
 */
class ImgPanel1 extends JPanel {
    private String imgPath;
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    @Override
    protected void paintComponent(Graphics g) {
        String basePath = System.getProperty("user.dir");
        Image bg = null;
        try {
            bg = ImageIO.read(new File(basePath + "/until/src/img/" + imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
