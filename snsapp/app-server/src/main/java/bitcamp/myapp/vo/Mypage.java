package bitcamp.myapp.vo;

import java.sql.Timestamp;
import java.util.Objects;

public class Mypage extends Member {

  public static final char MALE = 'M';
  public static final char FEMALE = 'W';
  private static final long serialVersionUID = 1L;
  private String stateMessage;
  private String filePath;
  private Timestamp birthDay;
  private char gender;
  private int likes;
  private int todayVisitCount;
  private int visitCount;
  private String email;
  private String photo;
  private Timestamp createdDate;

  @Override
  public String toString() {
    return "Mypage{" +
        "state_Message='" + stateMessage + '\'' +
        ", filePath='" + filePath + '\'' +
        ", birthDay=" + birthDay +
        ", gender=" + gender +
        ", likes=" + likes +
        ", today_Visit_Count=" + todayVisitCount +
        ", visit_Count=" + visitCount +
        ", email='" + email + '\'' +
        ", photo='" + photo + '\'' +
        ", created_Date=" + createdDate +
        '}';
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (!(o instanceof Mypage mypage)) {
          return false;
      }
    return gender == mypage.gender
        && likes == mypage.likes
        && todayVisitCount == mypage.todayVisitCount
        && visitCount == mypage.visitCount
        && Objects.equals(stateMessage, mypage.stateMessage)
        && Objects.equals(filePath, mypage.filePath)
        && Objects.equals(birthDay, mypage.birthDay)
        && Objects.equals(email, mypage.email)
        && Objects.equals(photo, mypage.photo)
        && Objects.equals(createdDate, mypage.createdDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stateMessage, filePath, birthDay, gender, likes, todayVisitCount,
        visitCount, email, photo, createdDate);
  }

  public String getStateMessage() {
    return stateMessage;
  }

  public void setStateMessage(String stateMessage) {
    this.stateMessage = stateMessage;
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

  public int getTodayVisitCount() {
    return todayVisitCount;
  }

  public void setTodayVisitCount(int todayVisitCount) {
    this.todayVisitCount = todayVisitCount;
  }

  public int getVisitCount() {
    return visitCount;
  }

  public void setVisitCount(int visitCount) {
    this.visitCount = visitCount;
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

  public Timestamp getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
  }

}
