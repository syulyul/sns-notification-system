package bitcamp.myapp.vo;

import java.io.Serializable;

public class BoardLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private int memberNo;
    private int boardNo;

    @Override
    public String toString() {
        return "BoardLike{" +
                "memberNo=" + memberNo +
                ", boardNo=" + boardNo +
                '}';
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
