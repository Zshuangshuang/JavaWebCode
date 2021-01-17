import java.io.File;
import java.io.IOException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-17 19:39
 */
public class FileDemo2 {
    //实现遍历多级目录
    public static void listAllFiles(File file){
        //1、先判断当前file是否为目录
        if (file.isDirectory()){
            //2、找到当前目录下面的所有文件
            File[] files = file.listFiles();
            //3、遍历files
            for (File f : files){
                listAllFiles(f);
            }

        }else {
            System.out.println(file);
        }
    }
    
    public static void main(String[] args) throws IOException {
        /*File file = new File("d:/demo/libs/test1/test2");
        //创建多级目录
        file.mkdirs();
        File f = new File("d:/demo/hello.txt");
        //创建文件
        f.createNewFile();*/
        File file = new File("d:/demo");
        listAllFiles(file);
    }

}
