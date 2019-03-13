package com.java.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Random;

public class Line {
    public static void main(String[] args) throws IOException {
        //指定图片大小和类型
        BufferedImage bufferedImage = new BufferedImage(1260, 970, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics graphics = bufferedImage.getGraphics();
        //获取字体
        Font font = new Font("方正克书皇榜体 简", Font.BOLD, 20);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        //绘制一个白色矩形
        graphics.fillRect(0, 0, 1260, 970);
        //生成两个随机点
        Random random = new Random();
        int x1 = random.nextInt(1260);
        int y1 = random.nextInt(970);
        int x2 = random.nextInt(1260);
        int y2 = random.nextInt(970);
        //控制台输出两点
        System.out.println("点1:(" + x1 + "," + y1 + ") 点2:(" + x2 + "," + y2 + ")");
        //计算两点距离
        double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        DecimalFormat df = new DecimalFormat("#.00");
        String string = df.format(distance);
        //控制台输出距离
        System.out.println("两点距离为" + string);
        //更改画笔颜色
        graphics.setColor(Color.BLACK);
        //绘制线段
        graphics.drawLine(x1, y1, x2, y2);
        //写上线段长度
        graphics.drawString(string,(x1+x2)/2,(y1+y2)/2);
        //输出图片,并指定文件路径
        File file = new File("D:/line.png");
        //获取输出流
        OutputStream outputStream = new FileOutputStream(file);
        //将图片从缓冲区通过字节写到文件
        ImageIO.write(bufferedImage, "png", outputStream);
        //关闭输出流
        outputStream.close();
    }
}