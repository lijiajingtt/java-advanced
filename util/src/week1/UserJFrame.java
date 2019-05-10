package week1;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserJFrame extends JFrame implements ActionListener{
    private JTextArea textUser;//文本域
    private int number=1;//学号
    private JTextField textNumber,textName;//显示学号和姓名的文本
    private JRadioButton rbMale,rbFmale;//
    private JComboBox comProvince,combCities;//下拉菜单形式的复选框，用于存放省份和城市
    private JButton btnAdd;//添加按钮
    private Object[][] cities;//声明全局变量，监听comProvince把combCities换成在所选省份里的城市
    private String fileName="student.dat";//保存数据的文件名
    //输入输出流
    private ObjectOutputStream out=null;
    private ObjectInputStream in = null;
    private Object objs[]=new Object[0];//保存数据，点击添加按钮，就把数组objs[]重新向文件中写入数据一次。则关闭图形界面，再次运行还能显示上次保存的额学生信息
    public UserJFrame(Object[] privinces,Object[][] cities){

        super("输入信息");
        this.cities=cities;
        setBounds(300,230,560,200);//设置图形界面位置，大小
        setDefaultCloseOperation(EXIT_ON_CLOSE);//设置图形界面右上红色关闭按钮

        getContentPane().setLayout(new GridLayout(1, 2));//设置网格布局：一行两列
        textUser=new JTextArea();
        Font font=new Font("aaa", Font.BOLD, 18);//new字体
        textUser.setFont(font);//对添加到文本域的字设置字体
        textUser.setForeground(Color.green);//对添加到文本域的字设置颜色
        getContentPane().add(new JScrollPane(textUser));//添加文本域

        JPanel p=new JPanel();//new一个面板
        getContentPane().add(p);//添加面板
        p.setLayout(new GridLayout(6, 1));//对面板设置网格布局：六行一列
        textNumber=new JTextField();//
        objs=readObjectFromFile();//
        number=objs.length+1;//
        textNumber.setText(""+number);//学生编号，从1号开始累加
        textNumber.setEditable(false);//设置文本不可编辑
        p.add(textNumber);//面板添加学号编辑框
        textName=new JTextField("姓名");//添加姓名的文本，默认显示值为"姓名"
        p.add(textName);//面板添加姓名编辑框
        //new一个面板添加性别选择按钮
        JPanel pS=new JPanel();
        pS.setLayout(new GridLayout(1, 2));//布局
        p.add(pS);
        rbMale=new JRadioButton("男",true);//设置默认选中男
        rbFmale=new JRadioButton("女");
        ButtonGroup group=new ButtonGroup();//为一组按钮创建一个多斥作用域
        group.add(rbFmale);
        group.add(rbMale);
        pS.add(rbMale);
        pS.add(rbFmale);

        comProvince=new JComboBox( privinces );//new省份，并把privinces[]加入复选框，默认显示privinces[0]
        p.add(comProvince);
        comProvince.addActionListener(this);//添加监听，再实现接口implements ActionListener，最后实现抽象方法public void actionPerformed(ActionEvent e)，监听要实现的步骤在方法完成
        combCities=new JComboBox(cities[0]);//new城市，并把cities[0]加入复选框，默认显示cities[0][0]
        p.add(combCities);

        btnAdd=new JButton("添加");
        p.add(btnAdd);
        btnAdd.addActionListener(this);//监听
//        textUser.setText(readFromFile());//从存储信息的文件中读出上次存储的信息，并显示在文本域中
        setVisible(true);//显示图形界面
    }
    public static void main(String[] args) {
        Object[] privinces={"湖南省","江苏省","浙江省"};
        Object cities[][]= {
                {
                        "长沙", "益阳","衡阳","湘潭","娄底"
                },
                {
                        "南京","苏州","无锡"
                },
                {
                        "杭州","宁波","温州"
                }
        };
        new UserJFrame(privinces,cities);//值传递
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==comProvince){//当选择某一个省份，把城市换成对应省份的城市
            int i=comProvince.getSelectedIndex();//获得省份序号
            combCities.removeAllItems();//把之前添加的城市移除
            for(int j=0;j<cities[i].length;j++){
                combCities.addItem(cities[i][j]);//把所有第i个省份内的城市添加入复选框
            }
        }
        if(e.getSource()==btnAdd){//判断是否是点击添加按钮的动作
            student s=new student();
            //学号---收集并更新
            s.setNumber(number);
            number++;
            textNumber.setText(""+number);

            //姓名
            s.setName(textName.getText());
            textName.setText("姓名");

            //性别
            if(rbMale.isSelected()){
                s.setSex(rbMale.getText());
            }else{
                s.setSex(rbFmale.getText());
            }
            //省份
            s.setProvince((String)comProvince.getSelectedItem());
            //城市
            s.setCity((String)combCities.getSelectedItem());
            //向存储数据的数组中添加信息
            Object objs2[]=new Object[objs.length+1];
            for (int j = 0; j < objs.length; j++) {
                objs2[j] = objs[j];
            }
            objs2[objs.length]=s;
            objs=objs2;
            write2File(objs);//每次添加学生信息，都会向文件中重新写入所有信息
            textUser.append(s.toString()+"\n");//或者         textUser.setText(readFromFile());//在文本域中显示学生信息
        }
    }

    //从文件中把所有对象读取出来，并且拼接成一个字符串返回
    private String readFromFile() {
        String str="";
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));//in流，必须套接new
            Object[] objs=(Object[])in.readObject();
            for (Object obj:objs) {
                str =str+ obj.toString() + "\r\n";
            }

        } catch (EOFException e) {

        } catch (Exception e) {
//              e.printStackTrace();
            System.out.println("软件第一次运行，数据为空");
            return "";
        }
        return str;

    }
    private Object[] readObjectFromFile() {
        Object objs[]=null;
        int i=0;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            objs=(Object[])in.readObject();//从文件中读取信息存储到数组中
        } catch (EOFException e) {

        } catch (Exception e) {
//          e.printStackTrace();
            System.out.println("软件第一次运行，数据为空");
            return new Object[0];
        }
        return objs;
    }
    //把对象s写到一个文件中（例如：student.dat）

    private void write2File(Object[] objs2) {
        try {
            out=new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(objs2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
