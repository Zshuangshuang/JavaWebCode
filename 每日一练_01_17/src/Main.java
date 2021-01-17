import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-17 22:21
 */
class Person{
    public String name;
    public int score;

    public Person(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            int option = scanner.nextInt();
            List<Person> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(new Person(scanner.next(),scanner.nextInt()));
            }
            //降序
            if (option == 0){
                list.sort(new Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {
                        return o2.score-o1.score;
                    }
                });
            }else if (option == 1){
                list.sort(new Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {
                        return o1.score-o2.score;
                    }
                });
            }
            for (Person person : list){
                System.out.println(person.name+" "+person.score);
            }
        }
    }
}
