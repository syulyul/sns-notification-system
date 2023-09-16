package bitcamp.myapp.vo;

import java.util.Objects;

public class PersonalOption extends Member {

  private static final long serialVersionUID = 1L;

  private int likeNotiState;
  private int followNotiState;
  private int commentNotiState;
  private int tagNotiState;

  @Override
  public String toString() {
    return "PersonalOption{" +
        "likeNotiState='" + likeNotiState + '\'' +
        ", followNotiState='" + followNotiState + '\'' +
        ", commentNotiState='" + commentNotiState + '\'' +
        ", tagNotiState='" + tagNotiState + '\'' +
        "} " + super.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PersonalOption that)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(likeNotiState, that.likeNotiState)
        && Objects.equals(followNotiState, that.followNotiState)
        && Objects.equals(commentNotiState, that.commentNotiState)
        && Objects.equals(tagNotiState, that.tagNotiState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), likeNotiState, followNotiState, commentNotiState,
        tagNotiState);
  }

  public int getLikeNotiState() {
    return likeNotiState;
  }

  public void setLikeNotiState(int likeNotiState) {
    this.likeNotiState = likeNotiState;
  }

  public int getFollowNotiState() {
    return followNotiState;
  }

  public void setFollowNotiState(int followNotiState) {
    this.followNotiState = followNotiState;
  }

  public int getCommentNotiState() {
    return commentNotiState;
  }

  public void setCommentNotiState(int commentNotiState) {
    this.commentNotiState = commentNotiState;
  }

  public int getTagNotiState() {
    return tagNotiState;
  }

  public void setTagNotiState(int tagNotiState) {
    this.tagNotiState = tagNotiState;
  }
}
