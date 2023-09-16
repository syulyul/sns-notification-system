package bitcamp.myapp.service;

import bitcamp.myapp.dao.MyPageDao;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.MyPage;
import java.util.List;

public class DefaultMyPageService implements MyPageService {

  MyPageDao myPageDao;

  {
    System.out.println("DefaultMyPageService 생성됨!");
  }

  public DefaultMyPageService(MyPageDao myPageDao) {
    this.myPageDao = myPageDao;
  }

  @Override
  public int add(MyPage myPage) throws Exception {
    return myPageDao.insert(myPage);
  }

  @Override
  public MyPage get(int memberNo) throws Exception {
    return myPageDao.findBy(memberNo);
  }

  @Override
  public int update(MyPage myPage) throws Exception {
    return myPageDao.update(myPage);
  }

  @Override
  public int delete(int memberNo) throws Exception {
    return myPageDao.delete(memberNo);
  }

  @Override
  public int follow(int followerNo, int followingNo) throws Exception {
    return myPageDao.insertFollow(followerNo, followingNo);
  }

  @Override
  public int unfollow(int followerNo, int followingNo) throws Exception {
    return myPageDao.deleteFollow(followerNo, followingNo);
  }

  @Override
  public List<Member> followerList(int memberNo) throws Exception {
    return myPageDao.findAllFollowers(memberNo);
  }

  @Override
  public List<Member> followingList(int memberNo) throws Exception {
    return myPageDao.findAllFollowings(memberNo);
  }
}
