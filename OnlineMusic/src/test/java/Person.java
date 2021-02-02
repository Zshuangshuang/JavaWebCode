import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Author:ZouDouble
 * Description:测试json的用法,将对象转化为json字符串
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-02 10:43
 */
public class Person {
    private int id;
    private String name;
    private String password;
    public Person() {
        super();
    }
    public Person(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person(1, "tom", "123");
        String jsonString = objectMapper.writeValueAsString(person);
        System.out.println("JsonString: " + jsonString);
    }
}
