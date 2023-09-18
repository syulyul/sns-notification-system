package bitcamp.myapp.service;

import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.MyPage;

import java.util.List;

public interface MyPageService {

    int add(MyPage myPage) throws Exception;

    MyPage get(int memberNo) throws Exception;

    int update(MyPage myPage) throws Exception;

    int delete(int memberNo) throws Exception;

    int follow(Member follower, int followingNo) throws Exception;

    int unfollow(Member follower, int followingNo) throws Exception;

    List<Member> followerList(int memberNo) throws Exception;

    List<Member> followingList(int memberNo) throws Exception;
}
