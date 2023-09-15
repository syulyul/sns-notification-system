package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class BoardPhoto implements Serializable{
    private static final long serialVersionUID = 1L;

    int no; // 사진 번호
    int boardNo; // 게시글 번호
    String filePath; // 사진 파일

    @Override
    public String toString() {
        return "BoardPhoto{" +
                "no=" + no +
                ", boardNo=" + boardNo +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardPhoto that = (BoardPhoto) o;
        return no == that.no && boardNo == that.boardNo && Objects.equals(filePath, that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, boardNo, filePath);
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