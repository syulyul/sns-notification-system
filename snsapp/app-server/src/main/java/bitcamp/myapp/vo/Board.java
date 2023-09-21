package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Board implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no; // 게시글 번호
  private String title; // 제목
  private String content; // 내용
  private Member writer; // 작성자

  private Member profile; // 회원 프사
  private int viewCount; // 조회수
  private int likes; // 좋아요
  private int category; // 카테고리
  private List<BoardPhoto> attachedFiles; // 사진 파일
  private Timestamp createdAt; // 등록일
  private Timestamp updateAt; // 수정일
  private List<BoardComment> comments; // 댓글

  @Override
  public String toString() {
    return "Board{" +
            "no=" + no +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", viewCount=" + viewCount +
            ", likes=" + likes +
            ", category=" + category +
            ", attachedFiles=" + attachedFiles +
            ", createdAt=" + createdAt +
            ", updateAt=" + updateAt +
            '}';

  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (!(o instanceof Board board)) {
      return false;
    }
    return getNo() == board.getNo();

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

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public List<BoardPhoto> getAttachedFiles() {
    return attachedFiles;
  }

  public void setAttachedFiles(List<BoardPhoto> attachedFiles) {
    this.attachedFiles = attachedFiles;
  }

  public Timestamp getUpdateAt() {
    return updateAt;
    
  }

  public void setUpdateAt(Timestamp updateAt) {
    this.updateAt = updateAt;
  }

  public List<BoardComment> getComments() {
    return comments;
  }

  public void setComments(List<BoardComment> comments) {
    this.comments = comments;

  }

  public Member getProfile() {
    return profile;
  }

  public void setProfile(Member profile) {
    this.profile = profile;
  }
}
