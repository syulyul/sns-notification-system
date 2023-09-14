package bitcamp.myapp.vo;

import java.io.Serializable;

public class BoardPhoto implements Serializable{
  private static final long serialVersionUID = 1L;

  int no; // 사진 번호
  int boardNo; // 게시글 번호
  String filePath; // 사진 파일

  @Override
  public String toString() {
    return "AttachedFile{" +
            "no=" + no +
            ", filePath='" + filePath + '\'' +
            ", boardNo=" + boardNo +
            '}';
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public int getBoardNo() {
    return boardNo;
  }

  public void setBoardNo(int boardNo) {
    this.boardNo = boardNo;
  }
}