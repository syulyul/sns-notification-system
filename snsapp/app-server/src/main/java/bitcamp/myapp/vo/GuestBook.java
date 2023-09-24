package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class GuestBook implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no; // 게시글 번호
  private String title; // 제목
  private String content; // 내용
  private Member writer; // 작성자
  private Member toUser; // 당사자
  private Member profile; // 회원 프사
  private int likes; // 좋아요
  private Timestamp createdAt; // 등록일

  @Override
  public String toString() {
    return "GuestBook{" +
            "no=" + no +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", writer=" + writer +
            ", toUser=" + toUser +
            ", profile=" + profile +
            ", likes=" + likes +
            ", createdAt=" + createdAt +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GuestBook guestBook = (GuestBook) o;
    return no == guestBook.no && likes == guestBook.likes && Objects.equals(title, guestBook.title) && Objects.equals(content, guestBook.content) && Objects.equals(writer, guestBook.writer) && Objects.equals(toUser, guestBook.toUser) && Objects.equals(profile, guestBook.profile) && Objects.equals(createdAt, guestBook.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(no, title, content, writer, toUser, profile, likes, createdAt);
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
  }

  public Member getToUser() {
    return toUser;
  }

  public void setToUser(Member toUser) {
    this.toUser = toUser;
  }

  public Member getProfile() {
    return profile;
  }

  public void setProfile(Member profile) {
    this.profile = profile;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}