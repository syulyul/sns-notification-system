package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class BoardComment implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no; // 댓글 번호
  private int boardNo; // 게시글 번호
  private int memberNo; // 회원 번호
  private String content; // 내용
  private Timestamp createdAt; // 댓글 작성 일시
  private Timestamp updateAt; // 댓글 수정 일시

  @Override
  public String toString() {
    return "BoardComment{" +
        "no=" + no +
        ", boardNo=" + boardNo +
        ", memberNo=" + memberNo +
        ", content='" + content + '\'' +
        ", createdAt=" + createdAt +
        ", updateAt=" + updateAt +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoardComment that = (BoardComment) o;
    return no == that.no;
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

  public int getBoardNo() {
    return boardNo;
  }

  public void setBoardNo(int boardNo) {
    this.boardNo = boardNo;
  }

  public int getMemberNo() {
    return memberNo;
  }

  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(Timestamp updateAt) {
    this.updateAt = updateAt;
  }
}
