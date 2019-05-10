package week1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SearchMain {

    private String searchDirPath;
    private String keyWords;

    public SearchMain(String searchDirPath, String keyWords){
        this.searchDirPath = searchDirPath;
        this.keyWords = keyWords;
    }

    //计算文件数量
    public static int count = 0;

    public static FileOutputStream fos = null;

    /**
     * Author: Yuxin.Yang(Jimmy)
     * Time: 2018年8月7日 上午9:02:45
     * Detail: 查询包含关键字的文件的路径
     */
    public void search(){
        File file = new File(searchDirPath);
        File[] files = file.listFiles();
        getFiles(files);
        System.out.println("count:" + count);
    }

    //递归搜索文件并写入搜索到的路径到文件
    public void getFiles(File[] files){
        FileInputStream fis = null;
        try{
            for(File file : files){
                count++;
                if(file.isDirectory()){
                    getFiles(file.listFiles());
                }else{
                    StringBuffer sb = new StringBuffer();
                    byte[] bytes = new byte[1024];
                    fis = new FileInputStream(file);
                    int len = 0;
                    while((len = fis.read(bytes)) != -1){
                        sb.append(new String(bytes, 0, len, "utf-8"));
                    }
                    fis.close();
                    if(sb.indexOf(keyWords) >= 0){
                        System.out.println("包含关键字(" + keyWords + ")的文件路径:" + file.getAbsolutePath());
                        fos.write(("包含关键字(" + keyWords + ")的文件路径:" + file.getAbsolutePath() + System.lineSeparator()).getBytes());
                        fos.flush();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}