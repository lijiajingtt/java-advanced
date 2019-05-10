package week1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
public class SearchUi {

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SearchUi searchUi = new SearchUi();
                searchUi.init();
            }
        });

    }

    public void init(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("搜索文件路径");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight());
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int scrollPaneWidth = (int) dimension.getWidth() - 15;
        int scrollPaneHeight = (int) dimension.getHeight() - 35;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight());
        //添加滚动条
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0, 0, scrollPaneWidth, scrollPaneHeight);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane);

        //初始坐标
        int x = 0;
        int y = 0;

        //加上输入要搜索的文件夹路径的提示文本
        int height = 30;
        JLabel searchDirPathLabel = new JLabel("请输入要搜索的文件夹的路径:");
        searchDirPathLabel.setBounds(x, y, scrollPaneWidth, height);
        searchDirPathLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(searchDirPathLabel);

        //加上输入要搜索的文件夹路径的文本框
        y += height + 20;
        height = 100;
        final JTextArea searchDirPathText = new JTextArea();
        searchDirPathText.setBounds(x, y, scrollPaneWidth, height);
        panel.add(searchDirPathText);

        //加上输入路径的文本框的提示文本
        y += height + 20;
        height = 30;
        JLabel searchedFilePathLabel = new JLabel("请输入生成的搜索结果文件的路径:");
        searchedFilePathLabel.setBounds(x, y, scrollPaneWidth, height);
        searchedFilePathLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(searchedFilePathLabel);

        //加上输入路径的文本框
        y += height + 20;
        height = 100;
        final JTextArea searchedFilePathText = new JTextArea();
        searchedFilePathText.setBounds(x, y, scrollPaneWidth, height);
        panel.add(searchedFilePathText);

        //加上输入关键字的文本框的提示文本
        y += height + 20;
        height = 30;
        JLabel keyWordsLabel = new JLabel("请输入需要搜索的关键字(如果有多个,用英文半角逗号隔开):");
        keyWordsLabel.setBounds(x, y, scrollPaneWidth, height);
        keyWordsLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(keyWordsLabel);

        //加上输入关键字的文本框
        y += height + 20;
        height = 100;
        final JTextArea keyWordsText = new JTextArea();
        keyWordsText.setBounds(x, y, scrollPaneWidth, height);
        panel.add(keyWordsText);

        //加上搜索按钮
        y += height + 20;
        height = 30;
        int searchBtnWidth = 100;
        JButton searchBtn = new JButton("搜索");
        searchBtn.setBounds((scrollPaneWidth - searchBtnWidth) / 2, y, searchBtnWidth, height);
        searchBtn.setHorizontalAlignment(JButton.CENTER);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchDirPath = searchDirPathText.getText().replaceAll(" ", "");
                String searchedFilePath = searchedFilePathText.getText().replaceAll(" ", "");
                File searchedFile = new File(searchedFilePath);
                FileOutputStream fos = null;
                try{
                    fos = new FileOutputStream(searchedFile);
                    if(!searchedFile.exists()){
                        searchedFile.createNewFile();
                    }
                    SearchMain.fos = fos;
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                String keyWordsStr = keyWordsText.getText();
                String[] keyWordsArr = null;
                if(keyWordsStr.indexOf(",") > 0){
                    keyWordsArr = keyWordsStr.split(",");
                }else{
                    keyWordsArr = new String[]{ keyWordsStr };
                }
                LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
                ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 100, 3600, TimeUnit.SECONDS, workQueue);
                for(int i = 0; i < keyWordsArr.length; i++){
                    String keyWords = keyWordsArr[i];
                    ThreadMain threadMain = new ThreadMain(i, searchDirPath, keyWords);
                    executor.execute(threadMain);
                }
                if(executor.isTerminated() && SearchMain.fos != null){
                    try {
                        SearchMain.fos.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        panel.add(searchBtn);

        //需要手动设置宽度高度(鼠标操作)
        //frame.pack();
        frame.setVisible(true);
    }

}