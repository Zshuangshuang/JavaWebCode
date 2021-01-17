import java.io.File;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-17 11:57
 */
public class FileDemo1 {
    public static void main(String[] args) {
        //实例化file对象
        File file = new File("d:/test/tmp1");
        //判断file是否存在
        System.out.println(file.exists());
        //判断file是否为目录
        System.out.println(file.isDirectory());
        //判断file是否是普通文件
        System.out.println(file.isFile());
        file.delete();
        file.mkdirs();

    }
}
