package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class Mypage implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final char MALE = 'M';
    public static final char FEMALE = 'W';

    private int no;
    private String state_Message;
    private String filePath;
    private Timestamp birthDay;
    private char gender;
    private int likes;
    private int today_Visit_Count;
    private int visit_Count;
    private String email;
    private String photo;
    private Timestamp created_Date;

    @Override
    public String toString() {
        return "Mypage{" +
                "no=" + no +
                ", state_Message='" + state_Message + '\'' +
                ", filePath='" + filePath + '\'' +
                ", birthDay=" + birthDay +
                ", gender=" + gender +
                ", likes=" + likes +
                ", today_Visit_Count=" + today_Visit_Count +
                ", visit_Count=" + visit_Count +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", created_Date=" + created_Date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mypage mypage)) return false;
        return no == mypage.no && gender == mypage.gender && likes == mypage.likes && today_Visit_Count == mypage.today_Visit_Count && visit_Count == mypage.visit_Count && Objects.equals(state_Message, mypage.state_Message) && Objects.equals(filePath, mypage.filePath) && Objects.equals(birthDay, mypage.birthDay) && Objects.equals(email, mypage.email) && Objects.equals(photo, mypage.photo) && Objects.equals(created_Date, mypage.created_Date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, state_Message, filePath, birthDay, gender, likes, today_Visit_Count, visit_Count, email, photo, created_Date);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getState_Message() {
        return state_Message;
    }

    public void setState_Message(String state_Message) {
        this.state_Message = state_Message;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Timestamp getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Timestamp birthDay) {
        this.birthDay = birthDay;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getToday_Visit_Count() {
        return today_Visit_Count;
    }

    public void setToday_Visit_Count(int today_Visit_Count) {
        this.today_Visit_Count = today_Visit_Count;
    }

    public int getVisit_Count() {
        return visit_Count;
    }

    public void setVisit_Count(int visit_Count) {
        this.visit_Count = visit_Count;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Timestamp getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(Timestamp created_Date) {
        this.created_Date = created_Date;
    }
}
