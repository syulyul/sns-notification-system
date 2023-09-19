package bitcamp.myapp.vo;

import java.sql.Timestamp;

public class MyPage extends Member {

  public static final char MALE = 'M';
  public static final char FEMALE = 'W';
  private static final long serialVersionUID = 1L;
  private String stateMessage;
  private Timestamp birthday;
  private char gender;
  private int likes;
  private int todayVisitCount;
  private int visitCount;
  private Timestamp createdDate;

  @Override
  public String toString() {
    return "Mypage{" +
        "state_Message='" + stateMessage + '\'' +
        ", birthday=" + birthday +
        ", gender=" + gender +
        ", likes=" + likes +
        ", today_Visit_Count=" + todayVisitCount +
        ", visit_Count=" + visitCount +
        ", created_Date=" + createdDate +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o); // 수정 금지
  }

  @Override
  public int hashCode() {
    return super.hashCode(); // 수정 금지
  }

  public String getStateMessage() {
    return stateMessage;
  }

  public void setStateMessage(String stateMessage) {
    this.stateMessage = stateMessage;
  }

  public Timestamp getBirthday() {
    return birthday;
  }

  public void setBirthday(Timestamp birthday) {
    this.birthday = birthday;
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


  public Timestamp getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
  }

}
