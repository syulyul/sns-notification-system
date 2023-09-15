package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Board implements Serializable{
  private static final long serialVersionUID = 1L;

  private int no; // 게시글 번호
  private String title; // 제목
  private String content; // 내용
  //  private Member writer; // 작성자
  private int viewCount; // 조회수
  private Timestamp createdDate; // 등록일
  private int category; // 카테고리
  private List<BoardPhoto> boardPhotos; // 사진 파일

  //추가된 항목 2개
  private Timestamp updateDate; // 수정일
  private int likes; // 좋아요

  @Override
  public String toString() {
    return "Board{" +
            "no=" + no +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", viewCount=" + viewCount +
            ", likes=" + likes +
            ", category=" + category +
            ", boardPhotos=" + boardPhotos +
            ", createdDate=" + createdDate +
            ", updateDate=" + updateDate +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Board board = (Board) o;
    return no == board.no && viewCount == board.viewCount && likes == board.likes && category == board.category && Objects.equals(title, board.title) && Objects.equals(content, board.content) && Objects.equals(boardPhotos, board.boardPhotos) && Objects.equals(createdDate, board.createdDate) && Objects.equals(updateDate, board.updateDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(no, title, content, viewCount, likes, category, boardPhotos, createdDate, updateDate);
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

//  public Member getWriter() {
//    return writer;
//  }
//
//  public void setWriter(Member writer) {
//    this.writer = writer;
//  }

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

  public List<BoardPhoto> getboardPhotos() {
    return boardPhotos;
  }

  public void setboardPhotos(List<BoardPhoto> boardPhotos) {
    this.boardPhotos = boardPhotos;
  }

  public Timestamp getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Timestamp updateDate) {
    this.updateDate = updateDate;
  }
}