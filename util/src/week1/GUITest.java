package week1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

public class GUITest {

    public static void main(String[] args) {
        new GetGUI();
    }
}

//GUI界面设置。
class GetGUI{
    JFrame f;
    JTextField tf;
    JButton but;
    JButton but1;
    JButton but2;
    JTextArea ta;
    JScrollPane scroll;

    JFileChooser JFC;
    JDialog dia;
    JLabel lab;
    JButton okBut;
    GetGUI(){
        f = new JFrame("多功能小窗口");
        tf = new JTextField("填写本地文本路径或网址(确认后请稍候...)",45);
        but = new JButton("转到");
        but2 = new JButton("打开");
        but1 = new JButton("保存");
        ta = new JTextArea(23,50);
        JFC = new JFileChooser();
//        设置选择可选文件夹和文件。
        JFC.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
//        设置可选文件后缀名（过滤器）。
        FileFilter filter = new FileNameExtensionFilter(".txt","txt");
        FileFilter filter1 = new FileNameExtensionFilter(".java","java");
        JFC.setFileFilter(filter);
        JFC.setFileFilter(filter1);

        dia = new JDialog(f,true);
        lab = new JLabel();
        okBut = new JButton("ok");

        dia.add(lab);
        dia.add(okBut);
        dia.setBounds(350,150,500,100);
        dia.setLayout(new FlowLayout());

//        文本区域设置滚动条。
        scroll = new JScrollPane(ta);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        f.add(tf);
        f.add(but);
        f.add(but2);
        f.add(but1);
        f.add(scroll);
        f.setBounds(300,100,600,550);
        f.setLayout(new FlowLayout());
        f.setResizable(false);
        setGUI();

        f.setVisible(true);
    }
    private void setGUI() {
        okBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dia.setVisible(false);
            }
        });

        dia.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                dia.setVisible(false);
            }
        });

//        通过地址栏上回车键打开本地文件或网站的html源代码。
        tf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    showContent();
                }
            }
        });

        tf.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e){
                tf.setText(null);
            }
        });

//        通过“转到”按钮打开本地文本文件或网站的html源代码。
        but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showContent();
            }
        });

//        通过“打开”按钮打开本地文本文件。
        but2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedReader bufr = null;
                int x = JFC.showOpenDialog(f);
                try {
                    if (x==JFileChooser.APPROVE_OPTION) {
                        ta.setText(null);
                        tf.setText(null);
                        File fi2 = JFC.getSelectedFile();
                        bufr = new BufferedReader(new FileReader(fi2));
                        String line =null;
                        tf.setText(fi2.getAbsolutePath());
                        while ((line=bufr.readLine())!=null) {
                            ta.append(line+"\r\n");
                        }
                    }
                } catch (FileNotFoundException e1) {
                    lab.setText("找不到文件");
                    dia.setVisible(true);
                } catch (IOException e1) {
                    lab.setText("读取失败");
                    dia.setVisible(true);
                }
            }
        });

//        通过“保存”按钮保存文件。
        but1.addActionListener(new ActionListener() {
            String address;
            @Override
            public void actionPerformed(ActionEvent e) {
//                弹出一个保存对话框，并返回int类型的值。
                int x = JFC.showSaveDialog(f);
                File fi = JFC.getSelectedFile();
//                利用返回的int值判断是否点击了对话框的保存按键
                if (x==JFileChooser.APPROVE_OPTION && fi!=null) {
                    address = fi.getAbsolutePath();
                    String ends = JFC.getFileFilter().getDescription();
                    if (address.toUpperCase().endsWith(ends.toUpperCase())) {
//                        如果文件使用的扩展名符合选定扩展名，则原名保存。
                        write2file();
                    } else {
//                        如果不是选定扩展名，则要加上选定扩展名
                        address = address+ends;
                        write2file();
                    }
                }
            }

            //            保存文件的具体实现方法。
            private void write2file() {
                BufferedWriter bufw = null;
                try {
                    bufw = new BufferedWriter(new FileWriter(address));
                    int count = ta.getLineCount();
                    for (int x = 0; x < count; x++) {
//                        获取行数，再通过行首位的偏移量获取每行内容。
                        String text = ta.getText(ta.getLineStartOffset(x),ta.getLineEndOffset(x)-ta.getLineStartOffset(x));
                        bufw.write(text);
                        bufw.flush();
                    }
                } catch (BadLocationException e1) {
                    lab.setText("行数错误");
                    dia.setVisible(true);
                } catch (IOException e2) {
                    lab.setText("保存失败");
                    dia.setVisible(true);
                } finally {
                    try {
                        bufw.close();
                    } catch (IOException e1) {
                        lab.setText("关闭资源失败");
                        dia.setVisible(true);
                    }
                }
            }
        });

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
            public void windowActivated(WindowEvent e1){
//                设置窗口激活状态的焦点。
                but.requestFocus();
            }
        });
    }

    //    打开本地文本文件和网址html源代码的具体实现方法。
    private  void showContent() {
        BufferedReader bufr = null;
        try {
            File file = new File(tf.getText());
            if (file!=null && file.isFile()) {
                ta.setText(null);
                bufr = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line=bufr.readLine())!=null) {
                    ta.append(line+"\r\n");
                }
            }
            else if (file!=null) {
                URL url = new URL(tf.getText());
                if (null!=url.openStream()) {
                    ta.setText(null);
                    URLConnection conn = url.openConnection();
                    bufr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while ((line=bufr.readLine())!=null) {
                        ta.append(line+"\r\n");
                    }
                    bufr.close();
                }else {
                    lab.setText("输入网址文件不存在，请重新输入");
                    dia.setVisible(true);
                }
            }
        } catch (FileNotFoundException e) {
            lab.setText("输入文件路径不存在，请重新输入");
            dia.setVisible(true);
        } catch (MalformedURLException e) {
            lab.setText("输入网址或路径不正确，请重新输入");
            dia.setVisible(true);
        } catch (IOException e) {
            ta.append(e.toString());
            lab.setText("读取文件失败，请重新输入");
            dia.setVisible(true);
        }
        finally {
            if (bufr!=null) {
                try {
                    bufr.close();
                } catch (Exception e2) {
                    lab.setText("关闭资源失败");
                    dia.setVisible(true);
                }
            }
        }
    }
}
