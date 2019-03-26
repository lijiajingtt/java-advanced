package com.java.util;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutPosition extends JFrame {
    public BorderLayoutPosition(){
        setTitle("这个窗体使用边界布局管理器");
        //定义一个容器
        Container c = getContentPane();
        //设置容器为边界布局管理器
        setLayout(new BorderLayout());
        JButton centerBtn = new JButton("中"),
                northBtn = new JButton("北"),
                southBtn = new JButton("南"),
                westBtn = new JButton("西"),
                eastBtn = new JButton("东");
        //中间添加按钮
        c.add(centerBtn,BorderLayout.CENTER);
        c.add(northBtn,BorderLayout.NORTH);
        c.add(southBtn,BorderLayout.SOUTH);
        c.add(westBtn,BorderLayout.WEST);
        c.add(eastBtn, BorderLayout.EAST);
        //设置窗体大小
        setSize(350,200);
        //设置窗体可见
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new  BorderLayoutPosition();
    }
}

