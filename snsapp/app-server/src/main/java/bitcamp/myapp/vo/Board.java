package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Board implements Serializable{
  private static final long serialVersionUID = 1L;

  private int no; // 게시글 번호
  private String title; // 제목
  private String content; // 내용
  // private Member writer; // 작성자
  private int viewCount; // 조회수
  private int likes; // 좋아요
  private int category; // 카테고리
  private List<BoardPhoto> attachedFiles; // 사진 파일
  private Timestamp createdDate; // 등록일
  private Timestamp updateDate; // 수정일

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
            ", createdDate=" + createdDate +
            ", updateDate=" + updateDate +
            '}';
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

  public Timestamp getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
  }

  public List<BoardPhoto> getAttachedFiles() {
    return attachedFiles;
  }

  public void setAttachedFiles(List<BoardPhoto> attachedFiles) {
    this.attachedFiles = attachedFiles;
  }

  public Timestamp getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Timestamp updateDate) {
    this.updateDate = updateDate;
  }
}
