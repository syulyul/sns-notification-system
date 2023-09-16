package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Member implements Serializable {

  private static final long serialVersionUID = 1L;



  private int no;
  private String nick;
  private String name;
  private int phone_Number;
  private String password;

  @Override
  public String toString() {
    return "Member{" +
            "no=" + no +
            ", nick='" + nick + '\'' +
            ", name='" + name + '\'' +
            ", phone_Number=" + phone_Number +
            ", password='" + password + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Member member)) return false;
    return no == member.no && phone_Number == member.phone_Number && Objects.equals(nick, member.nick) && Objects.equals(name, member.name) && Objects.equals(password, member.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(no, nick, name, phone_Number, password);
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

  public int getPhone_Number() {
    return phone_Number;
  }

  public void setPhone_Number(int phone_Number) {
    this.phone_Number = phone_Number;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
