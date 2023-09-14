package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Member implements Serializable {

  private static final long serialVersionUID = 1L;


  public static final char MALE = 'M';
  public static final char FEMALE = 'W';
  private int no;
  private String nick;
  private String name;
  private int phone_number;
  private String password;
  private String email;
  private String photo;
  private Date birthday;
  private char gender;
  private Date created_date;

  @Override
  public String toString() {
    return "sns_member{" +
            "no=" + no +
            ", nick='" + nick + '\'' +
            ", name='" + name + '\'' +
            ", phone_number=" + phone_number +
            ", password='" + password + '\'' +
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
    if (!(o instanceof sns_member snsMember)) return false;
    return getNo() == snsMember.getNo() && getPhone_number() == snsMember.getPhone_number() && getGender() == snsMember.getGender() && Objects.equals(getNick(), snsMember.getNick()) && Objects.equals(getName(), snsMember.getName()) && Objects.equals(getPassword(), snsMember.getPassword()) && Objects.equals(getEmail(), snsMember.getEmail()) && Objects.equals(getPhoto(), snsMember.getPhoto()) && Objects.equals(getBirthday(), snsMember.getBirthday()) && Objects.equals(getCreated_date(), snsMember.getCreated_date());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getNo(), getNick(), getName(), getPhone_number(), getPassword(), getEmail(), getPhoto(), getBirthday(), getGender(), getCreated_date());
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPhone_number() {
    return phone_number;
  }

  public void setPhone_number(int phone_number) {
    this.phone_number = phone_number;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
