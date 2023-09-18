package bitcamp.myapp.service;

import bitcamp.myapp.dao.MyPageDao;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.MyPage;
import bitcamp.myapp.vo.NotiLog;
import bitcamp.myapp.vo.NotiType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultMyPageService implements MyPageService {

    MyPageDao myPageDao;

    NotificationService notificationService;

    {
        System.out.println("DefaultMyPageService 생성됨!");
    }

    public DefaultMyPageService(MyPageDao myPageDao, NotificationService notificationService) {
        this.myPageDao = myPageDao;
        this.notificationService = notificationService;
    }

    @Transactional
    @Override
    public int add(MyPage myPage) throws Exception {
        return myPageDao.insert(myPage);
    }

    @Override
    public MyPage get(int memberNo) throws Exception {
        return myPageDao.findBy(memberNo);
    }

    @Transactional
    @Override
    public int update(MyPage myPage) throws Exception {
        return myPageDao.update(myPage);
    }

    @Transactional
    @Override
    public int delete(int memberNo) throws Exception {
        return myPageDao.delete(memberNo);
    }

    @Transactional
    @Override
    public int follow(Member follower, int followingNo) throws Exception {
        int result = myPageDao.insertFollow(follower.getNo(), followingNo);
        notificationService.add(new NotiLog(
                followingNo,
                NotiType.FOLLOW_TYPE,
                follower.getNick() + "님이 회원님을 팔로우 했습니다.",
                "/myPage/" + follower.getNo()));
        return result;
    }

    @Transactional
    @Override
    public int unfollow(Member follower, int followingNo) throws Exception {
        int result = myPageDao.deleteFollow(follower.getNo(), followingNo);
        notificationService.add(new NotiLog(
                followingNo,
                NotiType.FOLLOW_TYPE,
                follower.getNick() + "님이 회원님을 팔로우 취소 했습니다.",
                "/myPage/" + follower.getNo()));
        return result;
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
