package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class BoardLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private int memberNo; // 회원 번호
    private int boardNo; // 게시글 번호

    @Override
    public String toString() {
        return "BoardLike{" +
                "memberNo=" + memberNo +
                ", boardNo=" + boardNo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardLike boardLike = (BoardLike) o;
        return memberNo == boardLike.memberNo && boardNo == boardLike.boardNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, boardNo);
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }
}
