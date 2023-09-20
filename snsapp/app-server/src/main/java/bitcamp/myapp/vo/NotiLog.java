package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class NotiLog implements Serializable {

  public static final int NOT_READ_STATE = 0;
  public static final int READ_STATE = 1;
  private static final long serialVersionUID = 1L;
  private int no; // 알림 기록 번호
  private int memberNo; // 회원 번호
  private int notiTypeNo; //알림 타입 번호
  private String content;
  private String url;
  private int notiState;

  public NotiLog() {
  }

  public NotiLog(int memberNo, int notiTypeNo, String content, String url) {
    this.memberNo = memberNo;
    this.notiTypeNo = notiTypeNo;
    this.content = content;
    this.url = url;
  }

  @Override
  public String toString() {
    return "NotiLog{" +
        "no=" + no +
        ", memberNo=" + memberNo +
        ", notiNo=" + notiTypeNo +
        ", content='" + content + '\'' +
        ", url='" + url + '\'' +
        ", noti_State='" + notiState + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NotiLog notiLog)) {
      return false;
    }
    return no == notiLog.no
        && memberNo == notiLog.memberNo
        && notiTypeNo == notiLog.notiTypeNo
        && Objects.equals(content, notiLog.content)
        && Objects.equals(url, notiLog.url)
        && Objects.equals(notiState, notiLog.notiState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(no, memberNo, notiTypeNo, content, url, notiState);
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getMemberNo() {
    return memberNo;
  }

  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }

  public int getNotiNo() {
    return notiTypeNo;
  }

  public void setNotiNo(int notiNo) {
    this.notiTypeNo = notiNo;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getNotiState() {
    return notiState;
  }

  public void setNotiState(int notiState) {
    this.notiState = notiState;
  }
}
