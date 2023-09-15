package bitcamp.myapp.vo;

import java.io.Serializable;
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


  @Override
  public String toString() {
    return "Member{" +
            "no=" + no +
            ", nick='" + nick + '\'' +
            ", name='" + name + '\'' +
            ", phone_number=" + phone_number +
            ", password='" + password + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Member member)) return false;
    return getNo() == member.getNo() && getPhone_number() == member.getPhone_number() && Objects.equals(getNick(), member.getNick()) && Objects.equals(getName(), member.getName()) && Objects.equals(getPassword(), member.getPassword());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getNo(), getNick(), getName(), getPhone_number(), getPassword());
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
}
