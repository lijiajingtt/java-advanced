package week1;
public class ThreadMain implements Runnable{

    private int taskNum;
    private String searchDirPath;
    private String keyWords;

    public ThreadMain(int taskNum, String searchDirPath, String keyWords){
        this.taskNum = taskNum;
        this.searchDirPath = searchDirPath;
        this.keyWords = keyWords;
    }

    @Override
    public void run() {
        System.out.println("正在执行task " + taskNum);
        System.out.println("当前关键字:" + keyWords);
        SearchMain searchMain = new SearchMain(searchDirPath, keyWords);
        searchMain.search();
        System.out.println("task " + taskNum + "执行完毕");
    }

}