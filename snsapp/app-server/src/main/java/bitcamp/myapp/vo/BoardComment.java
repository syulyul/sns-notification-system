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
    private Timestamp createdDate; // 댓글 작성 일시
    private Timestamp updateDate; // 댓글 수정 일시

    @Override
    public String toString() {
        return "BoardComment{" +
                "no=" + no +
                ", boardNo=" + boardNo +
                ", memberNo=" + memberNo +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", updateDate=" + updateDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardComment that = (BoardComment) o;
        return no == that.no && boardNo == that.boardNo && memberNo == that.memberNo && Objects.equals(content, that.content) && Objects.equals(createdDate, that.createdDate) && Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, boardNo, memberNo, content, createdDate, updateDate);
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
