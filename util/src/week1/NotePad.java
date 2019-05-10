package week1;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NotePad {
    private JFrame frame;
    private JTextArea text;
    private File file;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NotePad window = new NotePad();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public NotePad() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("记事本");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mne = new JMenu("\u6587\u4EF6(F)");
        menuBar.add(mne);

        JMenuItem new_new = new JMenuItem("\u65B0\u5EFA");
        mne.add(new_new);
        //监听和处理新建
        new_new.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int ret = JOptionPane.showOptionDialog(null, "已经打开的文件尚未保存，需要保存吗？", "提示",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, null, null);
                if(ret == JOptionPane.NO_OPTION){

                    text.setText(null);
                }
                if(ret == JOptionPane.YES_OPTION){

                    //new JFileChooser();
                    FileDialog fd = new FileDialog(frame, "保存", FileDialog.SAVE);

                    if(file==null)
                    {
                        fd.setVisible(true);

                        String dirPath = fd.getDirectory();
                        String fileName = fd.getFile();
                        if(dirPath==null || fileName==null)
                            return ;
                        file = new File(dirPath,fileName);
                    }

                    try
                    {
                        BufferedWriter bufw  = new BufferedWriter(new FileWriter(file));
                        String text1 = text.getText();
                        bufw.write(text1);
                        bufw.close();
                    }
                    catch (IOException ex)
                    {
                        throw new RuntimeException();
                    }
                    text.setText(null);

                }
            }
        });


        JMenuItem open = new JMenuItem("\u6253\u5F00");
        mne.add(open);
        //监听和处理打开
        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fd = new FileDialog(frame, "打开", FileDialog.LOAD);
                fd.setVisible(true);
                String dir = fd.getDirectory();
                String f = fd.getFile();
                File f_open = new File(dir, f);
                try {
                    BufferedReader buf = new BufferedReader(new FileReader(f_open));

                    String line = null;
                    while((line=buf.readLine())!=null) {

                        text.append(line+"\r\n");
                    }
                    buf.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JMenuItem save = new JMenuItem("\u4FDD\u5B58");
        mne.add(save);
        //监听和处理保存
        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                FileDialog fd = new FileDialog(frame, "保存", FileDialog.SAVE);
                fd.setVisible(true);
                String dir = fd.getDirectory();
                String f = fd.getFile();
                File file = new File(dir, f);

                try {
                    BufferedWriter buf = new BufferedWriter(new FileWriter(file));

                    String s = text.getText();
                    buf.write(s);
                    buf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        JMenuItem exit = new JMenuItem("\u9000\u51FA");
        mne.add(exit);
        //监听和处理退出
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String s = text.getText();
                if(s==null) {
                    System.exit(0);
                }
                else {
                    int ret = JOptionPane.showOptionDialog(null, "是否将更改保存到 无标题？", "记事本",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, null, null);
                    if(ret == JOptionPane.NO_OPTION){

                        System.exit(0);
                    }
                    if(ret == JOptionPane.YES_OPTION){

                        FileDialog fd = new FileDialog(frame, "保存", FileDialog.SAVE);
                        fd.setVisible(true);
                        String dir = fd.getDirectory();
                        String f = fd.getFile();
                        File file = new File(dir, f);

                        try {
                            BufferedWriter buf = new BufferedWriter(new FileWriter(file));

                            String s1 = text.getText();
                            buf.write(s1);
                            buf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    }
                }

            }
        });

        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        text = new JTextArea();
        scrollPane.setViewportView(text);
    }


}
