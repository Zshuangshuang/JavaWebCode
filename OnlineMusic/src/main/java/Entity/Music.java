package Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Music {
    private int id;
    private String title;
    private String singer;
    private Date time;
    private String url;
    private int userId;

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", singer='" + singer + '\'' +
                ", time=" + time +
                ", url='" + url + '\'' +
                ", userId=" + userId +
                '}';
    }


}
