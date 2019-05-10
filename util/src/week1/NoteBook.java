package week1;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NoteBook {
    private JFrame mainFrame;
    private JTextArea mainWrite;
    private Container container;
    private JButton saveButton,loadButton;
    private JTextField savePath;
    private JLabel pathTip;
    private int distX = 100,distY = 100,mainFrameWidth = 520,mainFrameHeight = 500;
    private String title;
    JTextField getSavePath(){
        return savePath;
    }
    JButton getSaveButton(){
        return saveButton;
    }
    Container getContainer(){
        return container;
    }
    JTextArea getMainWrite(){
        return mainWrite;
    }
    JFrame getMainFrame(){
        return mainFrame;
    }
    JLabel getPathTip(){
        return pathTip;
    }
    JButton getLoadButton(){
        return loadButton;
    }
    void setLoadButton(){
        loadButton = new JButton("读取");
        loadButton.setBounds(325, 25, 65, 50);
        loadButton.addActionListener(new LoadListener());
        container.add(loadButton);
    }
    void flash(){
        mainFrame.setVisible(true);
    }
    void setMainFrame(){
        setTitle("记事本");
        mainFrame = new JFrame(getTitle());
        mainFrame.setBounds(distX, distY, mainFrameWidth, mainFrameHeight);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void setContainer(){
        container = mainFrame.getContentPane();
        container.setLayout(null);
    }
    void setMainWrite(){
        mainWrite = new JTextArea();
        mainWrite.setBounds(25, 100, 450, 380);
        mainWrite.setLineWrap(true);
        container.add(mainWrite);
    }
    void setSaveButton(){
        saveButton = new JButton("保存");
        saveButton.setBounds(400, 25, 75, 50);
        saveButton.addActionListener(new SaveListener());
        container.add(saveButton);
    }
    void setSavePath(){
        savePath = new JTextField("D:\\out.txt");
        savePath.setBounds(25, 25, 200, 50);
        container.add(savePath);
    }
    void setSavePath(String str){
        savePath.setText(str);
    }
    void setPathTip(){
        pathTip = new JLabel("数据反馈");
        pathTip.setBounds(250, 25, 75, 50);
        container.add(pathTip);
    }
    void setPathTip(String str){
        pathTip.setText(str);
    }
    String getTitle(){
        return title;
    }
    void setTitle(String str){
        title = str;
    }
    class SaveListener implements ActionListener{
        FileOutputStream fos = null;
        String overAdd;

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try{
                fos = new FileOutputStream(getSavePath().getText());
            }catch(IOException e){
                setPathTip("路径格式不正确！");
            }finally{
                try{
                    for(String str : getMainWrite().getText().split("\n")){
                        overAdd = str + "\r\n";
                        fos.write(overAdd.getBytes());
                    }
                    fos.close();
                    setPathTip("成功保存！");
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    }
    class LoadListener implements ActionListener{
        BufferedReader br = null;
        String lineRead = null;
        @Override
        public void actionPerformed(ActionEvent arg0) {
            getMainWrite().setText("");
            try{
                br = new BufferedReader(new FileReader(getSavePath().getText()));
            }catch(IOException e){
                setPathTip("路径格式不正确！");
            }finally{
                try {
                    while((lineRead = br.readLine()) != null){
                        getMainWrite().setText(getMainWrite().getText() + lineRead /* + "  \n  " */);     //主要疑问部分
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
    public NoteBook(){
        setMainFrame();
        setContainer();
        setMainWrite();
        setSaveButton();
        setSavePath();
        setPathTip();
        setLoadButton();
        flash();

    }
    public static void main(String[] args){
        new NoteBook();
    }

}