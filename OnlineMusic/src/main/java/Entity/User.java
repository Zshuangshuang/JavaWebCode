package Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-02 10:21
 */
@Getter
@Setter
public class User {
    private int id;
    private String username;
    private String password;
    private String gender;
    private int age;
    private String email;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
