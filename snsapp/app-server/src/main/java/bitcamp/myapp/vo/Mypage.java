package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Mypage implements Serializable {

    private static final long serialVersionUID = 1L;


    public static final char MALE = 'M';
    public static final char FEMALE = 'W';
    private int no;
    private int phone_number;
    private String email;
    private String photo;
    private Date birthday;
    private char gender;
    private Date created_date;

    @Override
    public String toString() {
        return "Mypage{" +
                "no=" + no +
                ", phone_number=" + phone_number +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", created_date=" + created_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mypage mypage)) return false;
        return no == mypage.no && getPhone_number() == mypage.getPhone_number() && getGender() == mypage.getGender() && Objects.equals(getEmail(), mypage.getEmail()) && Objects.equals(getPhoto(), mypage.getPhoto()) && Objects.equals(getBirthday(), mypage.getBirthday()) && Objects.equals(getCreated_date(), mypage.getCreated_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhone_number(), getEmail(), getPhoto(), getBirthday(), getGender(), getCreated_date());
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}
