package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class GuestBookLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private int memberNo;
    private int guestBookNo;

    @Override
    public String toString() {
        return "GuestBookLike{" +
                "memberNo=" + memberNo +
                ", guestBookNo=" + guestBookNo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestBookLike that = (GuestBookLike) o;
        return memberNo == that.memberNo && guestBookNo == that.guestBookNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, guestBookNo);
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getGuestBookNo() {
        return guestBookNo;
    }

    public void setGuestBookNo(int guestBookNo) {
        this.guestBookNo = guestBookNo;
    }
}
