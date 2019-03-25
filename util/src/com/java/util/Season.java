package com.java.util;

import javax.swing.*;
import java.awt.*;

public class Season extends JFrame {
    public Season() {
        setTitle("题目");
        Container container = getContentPane();
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        container.add(new JLabel("下面四句诗，哪一句是描写夏天的？"));
        container.add(new JButton("A.秋风萧瑟天气凉，树木摇荡露为霜。"));
        container.add(new JButton("B.白雪纷纷何所似，撒盐空中差可似。"));
        container.add(new JButton("C.接天莲叶无穷碧，映日荷花别样红。"));
        container.add(new JButton("D.竹外桃花两三枝，春江水暖鸭先知。"));
        setSize(300, 250);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new Season();
    }
}
