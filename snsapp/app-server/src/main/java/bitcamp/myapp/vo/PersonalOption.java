package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class PersonalOption implements Serializable {

    private static final long serialVersionUID = 1L;

    private int no;
    private String like_noti_state;
    private String follow_noti_state;
    private String coment_noti_state;
    private String tag_noti_state;

    @Override
    public String toString() {
        return "PersonalOption{" +
                "no=" + no +
                ", like_noti_state='" + like_noti_state + '\'' +
                ", follow_noti_state='" + follow_noti_state + '\'' +
                ", coment_noti_state='" + coment_noti_state + '\'' +
                ", tag_noti_state='" + tag_noti_state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalOption that)) return false;
        return no == that.no && Objects.equals(like_noti_state, that.like_noti_state) && Objects.equals(follow_noti_state, that.follow_noti_state) && Objects.equals(coment_noti_state, that.coment_noti_state) && Objects.equals(tag_noti_state, that.tag_noti_state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, like_noti_state, follow_noti_state, coment_noti_state, tag_noti_state);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getLike_noti_state() {
        return like_noti_state;
    }

    public void setLike_noti_state(String like_noti_state) {
        this.like_noti_state = like_noti_state;
    }

    public String getFollow_noti_state() {
        return follow_noti_state;
    }

    public void setFollow_noti_state(String follow_noti_state) {
        this.follow_noti_state = follow_noti_state;
    }

    public String getComent_noti_state() {
        return coment_noti_state;
    }

    public void setComent_noti_state(String coment_noti_state) {
        this.coment_noti_state = coment_noti_state;
    }

    public String getTag_noti_state() {
        return tag_noti_state;
    }

    public void setTag_noti_state(String tag_noti_state) {
        this.tag_noti_state = tag_noti_state;
    }
}
