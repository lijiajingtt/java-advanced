package week1;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;



public class ComponentPaintFrame extends JFrame {
    public   ComponentPaintFrame(){
        init();
        setTitle("组件绘制");
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private  void init(){
        //创建JPanel对象的同时，将背景图绘制上去
        JPanel jPanel = new JPanel(){
            protected  void paintComponent(Graphics g){
                try {
                    Image bg = ImageIO.read(new File("D:/bg.jpg"));
                    g.drawImage(bg,0,0,getWidth(),getHeight(),null);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        };
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER,200,200));
        add(jPanel);
        JButton jButton = new JButton(){
            protected void  paintComponent(Graphics g){
                try {
                    //获取图片并绘制在当前对象上
                    Image bg = ImageIO.read(new File("D:/bg.jpg"));
                    g.drawImage(bg,0,0,getWidth(),getHeight(),null);
                    g.drawString("按钮",100,45);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        };
        jButton.setPreferredSize(new Dimension(180,30));
        //不获取焦点
        jButton.setFocusPainted(false);
        //背景透明
        jButton.setContentAreaFilled(false);
        //无边框
        jButton.setBorderPainted(false);

        jPanel.add(jButton);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new ComponentPaintFrame();
    }

}

