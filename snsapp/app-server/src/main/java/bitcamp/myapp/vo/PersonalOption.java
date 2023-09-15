package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class PersonalOption implements Serializable {

    private static final long serialVersionUID = 1L;

    private int no;
    private String like_Noti_State;
    private String follow_Noti_State;
    private String coment_Noti_State;
    private String tag_Noti_State;

    @Override
    public String toString() {
        return "PersonalOption{" +
                "no=" + no +
                ", like_Noti_State='" + like_Noti_State + '\'' +
                ", follow_Noti_State='" + follow_Noti_State + '\'' +
                ", coment_Noti_State='" + coment_Noti_State + '\'' +
                ", tag_Noti_State='" + tag_Noti_State + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalOption that)) return false;
        return no == that.no && Objects.equals(like_Noti_State, that.like_Noti_State) && Objects.equals(follow_Noti_State, that.follow_Noti_State) && Objects.equals(coment_Noti_State, that.coment_Noti_State) && Objects.equals(tag_Noti_State, that.tag_Noti_State);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, like_Noti_State, follow_Noti_State, coment_Noti_State, tag_Noti_State);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getLike_Noti_State() {
        return like_Noti_State;
    }

    public void setLike_Noti_State(String like_Noti_State) {
        this.like_Noti_State = like_Noti_State;
    }

    public String getFollow_Noti_State() {
        return follow_Noti_State;
    }

    public void setFollow_Noti_State(String follow_Noti_State) {
        this.follow_Noti_State = follow_Noti_State;
    }

    public String getComent_Noti_State() {
        return coment_Noti_State;
    }

    public void setComent_Noti_State(String coment_Noti_State) {
        this.coment_Noti_State = coment_Noti_State;
    }

    public String getTag_Noti_State() {
        return tag_Noti_State;
    }

    public void setTag_Noti_State(String tag_Noti_State) {
        this.tag_Noti_State = tag_Noti_State;
    }
}
