package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class BoardComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private int no; // 댓글 번호
    private int boardNo; // 게시글 번호
    private Member writer; // 작성자
    private String content; // 내용
    private Timestamp createdAt; // 댓글 작성 일시
    private Timestamp updatedAt; // 댓글 수정 일시

    @Override
    public String toString() {
        return "BoardComment{" +
                "no=" + no +
                ", boardNo=" + boardNo +
                ", writer=" + writer +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardComment that = (BoardComment) o;
        return no == that.no && boardNo == that.boardNo && Objects.equals(writer, that.writer) && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, boardNo, writer, content, createdAt, updatedAt);
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

    public Member getWriter() {
        return writer;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
