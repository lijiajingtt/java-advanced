package week1;

import javax.swing.*;
import java.io.*;

public class TxtThread extends Thread {
    private JLabel txtLabel;

    public void setTxtLabel(JLabel txtLabel) {
        this.txtLabel = txtLabel;
    }

    @Override
    public void run() {
        File file = new File("D:/out.txt");
        InputStream inputStream = null;
        byte[] bytes = new byte[(int) file.length()];

        try {
            inputStream = new FileInputStream(file);
            inputStream.read(bytes);
        } catch (IOException e) {
            System.out.println("IO异常");
        }
        txtLabel.setText(new String(bytes));
    }
}
