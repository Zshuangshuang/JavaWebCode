/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-21 15:40
 */
public class Test {
    String str = new String("Hello");
    char[] ch = {'a','b'};
    public void change(String str,char[] ch){
        str = "test ok";
        ch[0] = 'c';
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.change(test.str,test.ch);
        System.out.println(test.str);
    }
}
