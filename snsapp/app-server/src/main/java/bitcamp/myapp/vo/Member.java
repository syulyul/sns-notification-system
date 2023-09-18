package bitcamp.myapp.vo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.Serializable;
import java.util.Objects;

@EntityScan
public class Member implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no;
  private String nick;
  private String name;
  private String phoneNumber;
  private String email;
  private String password;
  private String photo;

  @Override
  public String toString() {
    return "Member{" +
        "no=" + no +
        ", nick='" + nick + '\'' +
        ", name='" + name + '\'' +
        ", phoneNumber=" + phoneNumber +
        ", email='" + email + '\'' +
        ", photo='" + photo + '\'' +
        ", password='" + password + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Member member)) {
      return false;
    }
    return no == member.no;
  }

  @Override
  public int hashCode() {
    return Objects.hash(no);
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

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
